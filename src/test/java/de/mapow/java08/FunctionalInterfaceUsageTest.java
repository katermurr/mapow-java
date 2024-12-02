package de.mapow.java08;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-12-02
 */
class FunctionalInterfaceUsageTest {

    @Test
    void testOperator_CaseCompareWithAnonymousClassd() {
        // Using lambda expression
        final FunctionalInterfaceUsage<Integer> addOperation = (a, b) -> a + b;
        BDDAssertions.then(addOperation.process(3, 3)).isEqualTo(6);

        final FunctionalInterfaceUsage<String> appendOperation = (a, b) -> a + b;
        BDDAssertions.then(appendOperation.process("3", "3")).isEqualTo("33");

        final FunctionalInterfaceUsage<Integer> multiplyOperation = (a, b) -> a * b;
        BDDAssertions.then(multiplyOperation.process(3, 3)).isEqualTo(9);

        // Using anonymous class
        final FunctionalInterfaceUsage<Integer> anonymousClassAdd = new FunctionalInterfaceUsage<>() {
            @Override
            public Integer process(final Integer a, final Integer b) {
                return a + b;
            }
        };
        BDDAssertions.then(anonymousClassAdd.process(3, 3)).isEqualTo(6);
    }
}
