package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;



@DisplayName("Testing Google Maps - City.")
public class GoogleMapsTestCity {



    @ValueSource(strings = {
            "Sydney",
            "Melbourne"
    })
    @DisplayName("Testing Google Maps - City.")
    @ParameterizedTest(name = "Checking the city search: {0}")
    void searchCityTest(String testData) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        Selenide.open("https://www.google.com/maps");              //предусловие
        $("#searchboxinput").setValue(testData).pressEnter();             //шаги
        $$(".tAiQdd").find(Condition.text(testData)).shouldBe(visible);   //ожидаемый результат
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}