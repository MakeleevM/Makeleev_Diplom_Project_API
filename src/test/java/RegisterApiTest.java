import config.ApiConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.RegisterModel;
import model.SuccessRegisterModel;
import model.UnsuccessfulRegisterModel;
import org.aeonbits.owner.ConfigFactory;
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
    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешная регистрация пользователя")
    void successfulRegisterUserTest() {

        RegisterModel registerBody = new RegisterModel(config.password(), config.email());

        SuccessRegisterModel response = step("Запрос на регистрацию существующего пользователя", () ->
                given(requestSpec())
                        .when()
                        .body(registerBody)
                        .post("/register")
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_OK)
                        .body("id", notNullValue())
                        .extract().as(SuccessRegisterModel.class));

        step("Проверка Id", () ->
                assertThat(response.getId()).isNotNull());

        step("Проверка token", () ->
                assertThat(response.getToken()).isNotNull());
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация с незаполненными email/password")
    void unsuccessfulRegisterUserTest() {

        UnsuccessfulRegisterModel response = step("Передача запроса на регистрацию с незаполненными email/password", () ->
                given(requestSpec())
                        .when()
                        .body("{}")
                        .post("/register")
                        .then()
                        .spec(responseSpec())
                        .statusCode(SC_BAD_REQUEST)
                        .body("error", equalTo(testData.errorRegister))
                        .extract().as(UnsuccessfulRegisterModel.class));

        step("Проверка ответа", () ->
                assertThat(response.getError()).isEqualTo(testData.errorRegister));
    }
}
