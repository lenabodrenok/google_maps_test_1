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
    @ParameterizedTest(name = "Checking the city search: {0}")    // !!!!!! Добавить задержку перед проверкой
    void searchCityTest(String testData) {
        Selenide.open("https://www.google.com/maps");              //предусловие
        $("#searchboxinput").setValue(testData).pressEnter();             //шаги
        $$(".tAiQdd").find(Condition.text(testData)).shouldBe(visible);   //ожидаемый результат
    }

    @CsvSource(value = {
            "Sydney, Melbourne, directions_transit_grey800_24dp.png",
            "Melbourne, Hobart, flight_grey800_24dp.png"
    })
    @ParameterizedTest(name = "Проверка построения маршрута {0},{1}, ожидаем результат: {2}")
    void searchRouteTest(String testDataOne, String testDataTwo, String expectedResult) {
//        Предусловия:
        Selenide.open("https://www.google.com/maps/dir/");
//        Шаги:
        $("#sb_ifc51").setValue(testDataOne);
        $("#sb_ifc52").setValue(testDataTwo).pressEnter();

//        Ожидаемый результат:
        $$(".siAUzd-neVct").find(Condition.text(expectedResult)).shouldBe(visible);
    }




    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

    @Override     // посмотреть что это такое !!!!!!
    public String toString() {
        return "WebTest{}";
    }
}