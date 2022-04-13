package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleMapsTest {


    @ValueSource(strings = {
            "Sydney",
            "Melbourne"
    })
    @ParameterizedTest(name = "Checking the city search: {0}")
    void routTest(String testData) {
        Selenide.open("https://www.google.com/maps");              //предусловие
        $("#searchboxinput").setValue(testData).pressEnter();             //шаги
        $$(".tAiQdd").find(Condition.text(testData)).shouldBe(visible);   //ожидаемый результат
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

    @Override
    public String toString() {
        return "WebTest{}";
    }
}