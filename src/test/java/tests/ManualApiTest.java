package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("ReqRes API")
@Feature("Ручное тестирование")
@Story("Проверки, выполняемые вручную")
@Tag("manual")
public class ManualApiTest {

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка документации API на reqres.in")
    @Description("""
            Предусловия: открыт браузер.
            Шаги:
            1. Перейти на https://reqres.in/
            2. Открыть раздел Documentation / Examples
            3. Убедиться, что описаны методы GET, POST, PUT, PATCH, DELETE
            4. Сверить примеры request/response с актуальным поведением API
            Ожидаемый результат: документация доступна, примеры соответствуют API.
            """)
    void checkApiDocumentationManually() {
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка ответа API при невалидном x-api-key")
    @Description("""
            Предусловия: установлен Postman или аналог.
            Шаги:
            1. Отправить GET https://reqres.in/api/users/2 с заголовком x-api-key: invalid
            2. Проверить статус-код и тело ответа
            Ожидаемый результат: 401 Unauthorized, сообщение об ошибке API key.
            """)
    void checkInvalidApiKeyManually() {
    }

    @Test
    @Owner("Makeleev")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка rate limit при серии запросов")
    @Description("""
            Предусловия: валидный x-api-key.
            Шаги:
            1. Отправить подряд более 20 GET-запросов к /api/users
            2. Проверить заголовки Ratelimit-* и поведение при превышении лимита
            Ожидаемый результат: лимит отражается в заголовках, при превышении — отказ/ожидание.
            """)
    void checkRateLimitManually() {
    }
}
