package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.RegisterModel;
import model.SuccessRegisterModel;
import model.UnsuccessfulRegisterModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.Specification.requestSpec;
import static helpers.Specification.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("ReqRes API")
@Feature("Регистрация")
@Story("Успешная и неуспешная регистрация пользователя")
public class RegisterApiTest extends TestBase {

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешная регистрация пользователя")
    void successfulRegisterUserTest() {
        RegisterModel registerBody = new RegisterModel(config.password(), config.email());

        SuccessRegisterModel response = step("Отправить POST /register", () ->
                given(requestSpec())
                        .body(registerBody)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_OK)
                        .body("id", notNullValue())
                        .extract().as(SuccessRegisterModel.class));

        step("Проверить id и token", () -> {
            assertThat(response.getId()).isNotNull();
            assertThat(response.getToken()).isNotNull();
        });
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация с незаполненными email/password")
    void unsuccessfulRegisterUserTest() {
        UnsuccessfulRegisterModel response = step("Отправить POST /register без данных", () ->
                given(requestSpec())
                        .body("{}")
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_BAD_REQUEST)
                        .body("error", equalTo(testData.errorRegister))
                        .extract().as(UnsuccessfulRegisterModel.class));

        step("Проверить текст ошибки", () ->
                assertThat(response.getError()).isEqualTo(testData.errorRegister));
    }
}
