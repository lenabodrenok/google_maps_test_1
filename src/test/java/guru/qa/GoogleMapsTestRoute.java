package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Testing Google Maps - Route.")
public class GoogleMapsTestRoute {

    @CsvSource(value = {
            "Sydney, Melbourne, Transit",
            "Melbourne, Hobart, Flights"
    })
    @DisplayName("Testing Google Maps - Route.")
    @ParameterizedTest(name = "Checking the route {0} - {1}. Expected result: {2} route found")

    void searchRouteTest(String testDataOne, String testDataTwo, String expectedResult) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        Selenide.open("https://www.google.com/maps/dir/");
        $("#directions-searchbox-0").$(".tactile-searchbox-input").setValue(testDataOne);
        $("#directions-searchbox-1").$(".tactile-searchbox-input").setValue(testDataTwo).pressEnter();
        $$(".siAUzd-neVct").find(Condition.text(expectedResult)).shouldBe(hidden);
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}

