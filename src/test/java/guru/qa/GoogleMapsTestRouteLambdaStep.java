package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.nio.charset.StandardCharsets;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Testing Google Maps - Route.")
@Feature("Maps")
@Story("Search")
public class GoogleMapsTestRouteLambdaStep {

    @CsvSource(value = {
            "Sydney, Melbourne, Transit",
            "Melbourne, Hobart, Flights"
    })

    @DisplayName("Testing Google Maps - Route.")
    @ParameterizedTest(name = "Checking the route {0} - {1}. Expected result: {2} route found")
    void searchRouteTest(String testDataOne, String testDataTwo, String expectedResult) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        step("Open Google Maps", () -> {
            open("https://www.google.com/maps/dir/");
        });
        step("Build route " + testDataOne + "-" + testDataTwo, () -> {
            $("#directions-searchbox-0").$(".tactile-searchbox-input").setValue(testDataOne);
            $("#directions-searchbox-1").$(".tactile-searchbox-input").setValue(testDataTwo).pressEnter();
        });
        step(expectedResult + " route was found", () -> {
            $$(".siAUzd-neVct").find(Condition.text(expectedResult)).shouldBe(hidden);
                Allure.getLifecycle().addAttachment(
                    "Page sources",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
