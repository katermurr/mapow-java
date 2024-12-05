package eu.mapow.java08;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * https://howtodoinjava.com/java8/lambda-method-references-example/ <br>
 * Java 8 allows four types of method references:
 *
 * <pre>
    - ClassName::staticMethodName
    - instance::instanceMethodName
    - ClassName::instanceMethodName
    - ClassName::new
    The method references always utilize the :: operator.
 * </pre>
 *
 *
 * @author mapow
 * @since 2024-12-02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MethodReference {

    private static final Logger LOG = LoggerFactory.getLogger(MethodReference.class);

    static class Printer {
        void print(final String message) {
            LOG.debug("print: {}", message);
        }
    }

    @AllArgsConstructor
    static class Dto {
        @Getter
        private final String name;
    }

    public static Optional<Integer> staticMethodMax(final List<Integer> integers) {
        integers.stream().map(Object::toString).forEach(LOG::debug);
        return integers.stream().reduce(Math::max);
    }

    public static void instanceMethodReference(final List<String> messages) {
        final Printer printer = new Printer();
        messages.forEach(printer::print);
    }

    public static List<Integer> instanceMethodNameClass(final List<Integer> numbers) {
        // lambda version: numbers.stream().sorted((a, b) -> a.compareTo(b)).toList()
        return numbers.stream().sorted(Integer::compareTo).toList();
    }

    public static List<Dto> constructor(final String name) {
        final Function<String, Dto> dto = Dto::new;
        return Arrays.asList(dto.apply(name));
    }

}
