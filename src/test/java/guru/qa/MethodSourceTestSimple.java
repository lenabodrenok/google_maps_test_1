package guru.qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class MethodSourceTestSimple {
    static Stream<Arguments> methodSourceTestSimple() {
        return Stream.of(
                Arguments.of(List.of(1.5), "Sydney", "Melbourne"),
                Arguments.of(List.of(2), "Sydney", "Hobart")
        );
    }

    @MethodSource("methodSourceTestSimple")
    @ParameterizedTest(name = "methodSourceTestSimple")
    void methodSourceTestSimple(List<Float> valueOne, String valueTwo, String valueThree) {
        System.out.println(valueOne + " hour(s) flight from " + valueTwo + " to " + valueThree);
    }
}
