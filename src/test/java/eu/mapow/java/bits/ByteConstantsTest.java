package eu.mapow.java.bits;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2025-01-18
 */
class ByteConstantsTest {

  @Test
  void testByteConstants() {
    BDDAssertions.then(ByteConstants.MASK_BIT8).isEqualTo(Byte.parseByte("-128"));
    BDDAssertions.then(ByteConstants.MASK_BIT8).isEqualTo((byte) -0x80);
    BDDAssertions.then(ByteConstants.MASK_BIT8).isEqualTo((byte) -128);

    BDDAssertions.then(ByteConstants.MAXIMUM_BIT_INDEX).isEqualTo(7);
    BDDAssertions.then(ByteConstants.MINIMUM_BIT_INDEX).isEqualTo(0);
  }
}
