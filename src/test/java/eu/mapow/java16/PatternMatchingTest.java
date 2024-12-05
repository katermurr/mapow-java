package eu.mapow.java16;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-12-01
 */
class PatternMatchingTest {

    @Test
    void testCheckInstanceOf_CaseDouble() {
        // arrange
        final Double number = 5d;

        // act
        final String instanceOf = PatternMatching.checkInstanceOf(number);

        // assert
        BDDAssertions.then(instanceOf).isEqualTo("java.lang.Double - 5.0");
    }

    @Test
    void testCheckInstanceOf_CaseString() {
        // arrange
        final String string = "5";

        // act
        final String instanceOf = PatternMatching.checkInstanceOf(string);

        // assert
        BDDAssertions.then(instanceOf).isEqualTo("java.lang.String - 5");
    }

}
