import helpers.TestData;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    TestData testData = new TestData();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
