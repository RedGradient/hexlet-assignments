package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();

        var port = 5001;
        baseUrl = "http://localhost:" + port;
        app.start(5001);
    }

    @AfterAll
    static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(content).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateNewUser() {
        var firstName = "John";
        var lastName = "Doe";
        var email = "efgpbe4g@mail.com";
        var password = "12345";

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", firstName)
                .field("lastName", lastName)
                .field("email", email)
                .field("password", password)
                .asString();

        var user = new QUser()
                .firstName.equalTo(firstName)
                .lastName.equalTo(lastName)
                .email.equalTo(email)
                .password.equalTo(password)
                .findOne();

        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo(firstName);
        assertThat(user.getLastName()).isEqualTo(lastName);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);

        assertThat(response.getStatus()).isEqualTo(302);
    }

    @Test
    void testCreateNewUserWithIncorrectData() {
        var firstName = "John";
        var lastName = "Doe";
        var email = "efgpbe4g@mail.com";
        var password = "123";

        HttpResponse<String> response = Unirest
            .post(baseUrl + "/users")
            .field("firstName", firstName)
            .field("lastName", lastName)
            .field("email", email)
            .field("password", password)
            .asString();

        var user = new QUser()
            .firstName.equalTo(firstName)
            .lastName.equalTo(lastName)
            .email.equalTo(email)
            .password.equalTo(password)
            .findOne();


        assertThat(user).isNull();
        assertThat(response.getStatus()).isEqualTo(422);
    }
    // END
}
