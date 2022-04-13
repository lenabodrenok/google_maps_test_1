package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class GoogleMapsTestRoute {


    @CsvSource(value = {
            "Sydney, Melbourne, Transit",
            "Melbourne, Hobart, Flights"
    })
    @ParameterizedTest(name = "Проверка маршрута {0},{1}, ожидаем результат - найден маршрут: {2}")

    void searchRouteTest(String testDataOne, String testDataTwo, String expectedResult) {
//        Предусловия:
        Selenide.open("https://www.google.com/maps/dir/");
//        Шаги:
        $("#directions-searchbox-0").$(".tactile-searchbox-input").setValue(testDataOne);
        $("#directions-searchbox-1").$(".tactile-searchbox-input").setValue(testDataTwo).pressEnter();

//        Ожидаемый результат:
        $$(".siAUzd-neVct").find(Condition.text(expectedResult)).shouldBe(hidden);
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

