package guru.qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MethodSourceTestSimple {
    static Stream<Arguments> methodSourceTestSimple() {
        return Stream.of(
                Arguments.of("Age", List.of(5), "years"),
                Arguments.of("Height", List.of(43), "inch")
        );
    }

    @MethodSource("methodSourceTestSimple")
    @ParameterizedTest(name = "methodSourceTestSimple")
    void methodSourceTestSimple(String valueOne, List<Integer> valueTwo, String valueThree) {
        System.out.println(valueOne + " " + valueTwo + " " + valueThree);
    }
}
