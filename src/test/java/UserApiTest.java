import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.ListUsersModel;
import model.SuccessUserCreateModel;
import model.SuccessUserUpdate;
import model.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.Specification.requestSpec;
import static helpers.Specification.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("ReqRes API")
@Feature("Пользователи")
@Story("CRUD операции с пользователями")
public class UserApiTest extends TestBase {

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Получение списка пользователей с параметром page=2")
    void getListUsersTest() {

        step("Отправка запроса с query page " + testData.page + " и проверка тела ответа по json schema", () ->
                given(requestSpec())
                        .when()
                        .queryParam("page", testData.page)
                        .get("/users")
                        .then()
                        .spec(responseSpec())
                        .assertThat()
                        .statusCode(SC_OK)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_list_users.json"))
                        .body("page", equalTo(testData.page)));
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение пользователя по id")
    void getSingleUserByIdTest() {

        ListUsersModel response = step("Отправка запроса get users by id " + testData.id, () ->
                given(requestSpec())
                        .when()
                        .get("/users/" + testData.id)
                        .then()
                        .spec(responseSpec())
                        .assertThat()
                        .statusCode(SC_OK)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_single_users.json"))
                        .body("data.id", equalTo(testData.id))
                        .extract().jsonPath().getObject("data", ListUsersModel.class));

        step("Проверка модели пользователя", () -> {
            assertThat(response.getId()).isEqualTo(testData.id);
            assertThat(response.getEmail()).isEqualTo("janet.weaver@reqres.in");
            assertThat(response.getFirstName()).isEqualTo("Janet");
            assertThat(response.getLastName()).isEqualTo("Weaver");
            assertThat(response.getAvatar()).isEqualTo("https://reqres.in/img/faces/2-image.jpg");
        });
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание нового пользователя")
    void createNewUserTest() {

        UserModel userCreateBody = new UserModel(testData.name, testData.job);

        SuccessUserCreateModel response = step("Отправка запроса post /users", () ->
                given(requestSpec())
                        .when()
                        .body(userCreateBody)
                        .post("/users")
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_CREATED)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_create_user.json"))
                        .extract().response().as(SuccessUserCreateModel.class));

        step("Проверка, что id not null", () ->
                assertThat(response.getId()).isNotNull());

        step("Проверка, что name равен " + testData.name, () ->
                assertThat(response.getName()).isEqualTo(testData.name));

        step("Проверка, что job равен " + testData.job, () ->
                assertThat(response.getJob()).isEqualTo(testData.job));

        step("Проверка, что dateCreated равна " + testData.getDate(), () ->
                assertThat(response.getCreatedAt()).startsWith(testData.getDate()));
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Обновление данных пользователя (PATCH)")
    void updateUserTest() {

        UserModel userUpdateBody = new UserModel(testData.updatedName, testData.updatedJob);

        SuccessUserUpdate response = step("Отправка запроса patch /users/" + testData.id, () ->
                given(requestSpec())
                        .when()
                        .body(userUpdateBody)
                        .patch("/users/" + testData.id)
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_OK)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_update_user.json"))
                        .extract()
                        .as(SuccessUserUpdate.class));

        step("Проверка, что name равен " + testData.updatedName, () ->
                assertThat(response.getName()).isEqualTo(testData.updatedName));

        step("Проверка, что job равен " + testData.updatedJob, () ->
                assertThat(response.getJob()).isEqualTo(testData.updatedJob));

        step("Проверка, что updatedAT равна " + testData.getDate(), () ->
                assertThat(response.getUpdatedAt()).startsWith(testData.getDate()));
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление пользователя")
    void deleteUserTest() {

        step("Отправка запроса delete /users/" + testData.id, () ->
                given(requestSpec())
                        .when()
                        .delete("/users/" + testData.id)
                        .then()
                        .assertThat()
                        .statusCode(SC_NO_CONTENT));
    }
}
