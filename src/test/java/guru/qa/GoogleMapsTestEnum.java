package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.domain.MenuItem;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@DisplayName("Testing Google Maps MenuItem.")
public class GoogleMapsTestEnum {

    @DisplayName("Testing Google Maps MenuItem.")
    @EnumSource(MenuItem.class)
    @ParameterizedTest()
    void mapsMenuTest(MenuItem testData) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        step("Open Google Maps", () -> {
            Selenide.open("https://www.google.com/maps/dir/");
        });
        step("Check item: " + testData.rusName, () -> {
            $(".MJtgzc").$(byAttribute("data-tooltip", testData.rusName)).click();
            $("[aria-checked=true]").should(exist);
        });
    }
}
