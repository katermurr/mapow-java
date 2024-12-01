package de.mapow.java14;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-11-30
 */
class TextBlockTest {

    @Test
    void testTextBlock() {
        BDDAssertions.then(TextBlock.multilineQuery()).isEqualTo(TextBlock.query());
    }

}
