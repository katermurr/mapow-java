package de.mapow.java21;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author mapow
 * @since 2024-12-03
 */
class VirtualThreadsTest {

    @ParameterizedTest
    @CsvSource(value = { "false,20,25", "true,1,3" })
    void testRunThreads(final boolean isVirtualThreads, final double min, final double max) {
        // arrange
        final VirtualThreads virtualThreads = new VirtualThreads();
        // act
        final double duration = virtualThreads.runThreads(isVirtualThreads);
        // assert
        BDDAssertions.then(duration).isBetween(min, max);
    }

}
