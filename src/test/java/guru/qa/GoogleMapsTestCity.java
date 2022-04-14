package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


@DisplayName("Testing Google Maps")
public class GoogleMapsTestCity {

    @ValueSource(strings = {
            "Sydney",
            "Melbourne"
    })
    @DisplayName("Testing Google Maps")
    @ParameterizedTest(name = "Checking the city search: {0}")
    void searchCityTest(String testData) {
        Selenide.open("https://www.google.com/maps");              //предусловие
        $("#searchboxinput").setValue(testData).pressEnter();             //шаги
        $$(".tAiQdd").find(Condition.text(testData)).shouldBe(visible);   //ожидаемый результат
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}