package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;

import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN

        var users = new QUser().findList();
        var json = DB.json().toJson(users);
        ctx.json(json);

        // END
    }

    ;

    public void getOne(Context ctx, String id) {

        // BEGIN
        var normalizedId = Integer.parseInt(id);
        var user = new QUser().id.equalTo(normalizedId).findOne();
        var json = DB.json().toJson(user);
        ctx.json(json);
        // END
    }

    ;

    public void create(Context ctx) {

        // BEGIN

        var json = ctx.body();
        var user = DB.json().toBean(User.class, json);
        user.save();

        // END
    }

    ;

    public void update(Context ctx, String id) {
        // BEGIN

        var json = ctx.body();
        var user = DB.json().toBean(User.class, json);
        var normalizedId = Integer.parseInt(id);
        new QUser().id.equalTo(normalizedId)
                .asUpdate()
                    .set("firstName", user.getFirstName())
                    .set("lastName", user.getLastName())
                    .set("email", user.getEmail())
                    .set("password", user.getPassword())
                .update();

        // END
    }

    ;

    public void delete(Context ctx, String id) {
        // BEGIN

        var normalizedId = Integer.parseInt(id);
        new QUser().id.equalTo(normalizedId).delete();

        // END
    }

    ;
}
