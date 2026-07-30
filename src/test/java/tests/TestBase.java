package tests;

import config.ApiConfig;
import helpers.TestData;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    protected final TestData testData = new TestData();
    protected static final ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUri", config.baseUri());
        RestAssured.basePath = System.getProperty("basePath", config.basePath());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
