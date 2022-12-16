package exercise.controllers;

import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

import javax.servlet.http.HttpServletResponse;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        var firstNameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(x -> !x.isEmpty(), "first name is required field");
        var lastNameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(x -> !x.isEmpty(), "last name is required field");
        var emailValidator = ctx.formParamAsClass("email", String.class)
                .check(x -> !x.isEmpty(), "email is required field")
                .check(x -> x.contains("@"), "invalid email");
        var passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(x -> !x.isEmpty(), "password is required field")
                .check(x -> x.length() >= 4, "password length must not be shorter 4");

        var errors = JavalinValidation.collectErrors(
                firstNameValidator,
                lastNameValidator,
                emailValidator,
                passwordValidator
        );

        var user = new User(
                firstNameValidator.getStringValue(),
                lastNameValidator.getStringValue(),
                emailValidator.getStringValue(),
                passwordValidator.getStringValue()
        );

        if (!errors.isEmpty()) {
            ctx.res.setStatus(422);
            ctx.attribute("user", user);
            ctx.attribute("errors", errors);
            ctx.render("users/new.html");
            return;
        }

        user.save();
        ctx.sessionAttribute("flash", "Пользователь создан");
        ctx.redirect("/users");
        // END
    };
}
