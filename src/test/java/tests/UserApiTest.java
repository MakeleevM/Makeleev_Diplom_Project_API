package tests;

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static helpers.Specification.requestSpec;
import static helpers.Specification.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("ReqRes API")
@Feature("Пользователи")
@Story("CRUD операции с пользователями")
public class UserApiTest extends TestBase {

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Получение списка пользователей с параметром page=2")
    void getListUsersTest() {
        step("Отправить GET /users?page=" + testData.page, () ->
                given(requestSpec())
                        .queryParam("page", testData.page)
                        .when()
                        .get("/users")
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_OK)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_list_users.json"))
                        .body("page", equalTo(testData.page)));
    }

    @ParameterizedTest(name = "Получение пользователя по id={0}")
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("Получение пользователя по id")
    void getSingleUserByIdTest(int userId) {
        ListUsersModel response = step("Отправить GET /users/" + userId, () ->
                given(requestSpec())
                        .when()
                        .get("/users/" + userId)
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_OK)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_single_users.json"))
                        .body("data.id", equalTo(userId))
                        .body("data.email", notNullValue())
                        .extract().jsonPath().getObject("data", ListUsersModel.class));

        step("Проверить модель пользователя", () -> {
            assertThat(response.getId()).isEqualTo(userId);
            assertThat(response.getEmail()).isNotBlank();
            assertThat(response.getFirstName()).isNotBlank();
            assertThat(response.getLastName()).isNotBlank();
            assertThat(response.getAvatar()).isNotBlank();
        });
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание нового пользователя")
    void createNewUserTest() {
        UserModel userCreateBody = new UserModel(testData.name, testData.job);

        SuccessUserCreateModel response = step("Отправить POST /users", () ->
                given(requestSpec())
                        .body(userCreateBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_CREATED)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_create_user.json"))
                        .extract().as(SuccessUserCreateModel.class));

        step("Проверить id, name, job и createdAt", () -> {
            assertThat(response.getId()).isNotNull();
            assertThat(response.getName()).isEqualTo(testData.name);
            assertThat(response.getJob()).isEqualTo(testData.job);
            assertThat(response.getCreatedAt()).startsWith(testData.getDate());
        });
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Обновление данных пользователя (PATCH)")
    void updateUserTest() {
        UserModel userUpdateBody = new UserModel(testData.updatedName, testData.updatedJob);

        SuccessUserUpdate response = step("Отправить PATCH /users/" + testData.id, () ->
                given(requestSpec())
                        .body(userUpdateBody)
                        .when()
                        .patch("/users/" + testData.id)
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_OK)
                        .body(matchesJsonSchemaInClasspath("schemas/schema_update_user.json"))
                        .extract().as(SuccessUserUpdate.class));

        step("Проверить name, job и updatedAt", () -> {
            assertThat(response.getName()).isEqualTo(testData.updatedName);
            assertThat(response.getJob()).isEqualTo(testData.updatedJob);
            assertThat(response.getUpdatedAt()).startsWith(testData.getDate());
        });
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление пользователя")
    void deleteUserTest() {
        step("Отправить DELETE /users/" + testData.id, () ->
                given(requestSpec())
                        .when()
                        .delete("/users/" + testData.id)
                        .then()
                        .statusCode(SC_NO_CONTENT));
    }
}
