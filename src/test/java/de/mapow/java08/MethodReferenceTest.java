package de.mapow.java08;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import de.mapow.java08.MethodReference.Dto;
import de.mapow.testing.logging.LogEventCapture;

/**
 * @author mapow
 * @since 2024-12-02
 */
class MethodReferenceTest {

    @Test
    void testStaticMethodMax() {
        // arrange
        final List<Integer> ints = Arrays.asList(1, 12, 433, 5);

        // act
        final Optional<Integer> max = MethodReference.staticMethodMax(ints);

        // assert
        BDDAssertions.then(max.isPresent()).isTrue();
        BDDAssertions.then(max.get()).isEqualTo(433);
    }

    @Test
    void testInstanceMethodReference() {
        // arrange
        try (LogEventCapture logEventCapture = new LogEventCapture()) {
            final ListAppender<ILoggingEvent> logEventAppender = logEventCapture.start(MethodReference.class);
            final List<String> messages = Arrays.asList("Hello", "World");

            // act
            MethodReference.instanceMethodReference(messages);

            // assert
            BDDAssertions.then(logEventAppender.list).hasSize(2);
            BDDAssertions.then(logEventAppender.list.get(0).getFormattedMessage()).isEqualTo("print: Hello");
            BDDAssertions.then(logEventAppender.list.get(1).getFormattedMessage()).isEqualTo("print: World");
        }
    }

    @Test
    void testInstanceMethodNameClass() {
        // arrange
        final List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);

        // act
        final List<Integer> sorted = MethodReference.instanceMethodNameClass(numbers);

        // assert
        BDDAssertions.then(sorted).startsWith(2);
        BDDAssertions.then(sorted).endsWith(50);
        BDDAssertions.then(sorted).containsExactly(2, 3, 5, 9, 18, 24, 40, 50);
    }

    @Test
    void testConstructor() {
        // arrange
        final String expectedName = "Test325";

        // act
        final List<Dto> dtos = MethodReference.constructor(expectedName);

        // assert
        BDDAssertions.then(dtos).hasSize(1);
        BDDAssertions.then(dtos.get(0).getName()).isEqualTo(expectedName);
    }

}
