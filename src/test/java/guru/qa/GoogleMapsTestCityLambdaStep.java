package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.charset.StandardCharsets;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Testing Google Maps - City.")
@Feature("Maps")
@Story("Search")
public class GoogleMapsTestCityLambdaStep {

    @ValueSource(strings = {
            "Sydney",
            "Melbourne"
    })

    @DisplayName("Testing Google Maps - City.")
    @ParameterizedTest(name = "Checking the city search: {0}")
    void searchCityTestLambda(String testData) {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Google Maps", () -> {
            open("https://www.google.com/maps"); });
        step("Search " + testData, () -> {
            $("#searchboxinput").setValue(testData).pressEnter(); });
        step(testData + " was found", () -> {
            $$(".tAiQdd").find(Condition.text(testData)).shouldBe(visible);
            Allure.getLifecycle().addAttachment(
                        "Page sources",
                        "text/html",
                        "html",
                        WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
                );
            });
    }
}


