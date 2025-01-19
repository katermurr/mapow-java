package eu.mapow.java.bits;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author mapow
 * @since 2025-01-18
 */
class ByteUtilTest {

  @ParameterizedTest
  @ValueSource(bytes = { 1, 3, 5, 7, 9, 11 })
  void testIsBitSet_CaseFirstBit_ShouldBeTrue(final byte byteValue) {
    // act
    final boolean bitSet = ByteUtil.isBitSet(byteValue, 0);
    // assert
    BDDAssertions.then(bitSet).isTrue();
  }

  @ParameterizedTest
  @ValueSource(bytes = { 2, 4, 8, 16, 32, 64 })
  void testIsBitSet_CaseFirstBit_ShouldBeFalse(final byte byteValue) {
    // act
    final boolean bitSet = ByteUtil.isBitSet(byteValue, 0);
    // assert
    BDDAssertions.then(bitSet).isFalse();
  }

  @ParameterizedTest
  @ValueSource(bytes = { 2, 3, 6, 7, 10, 11, 14, 15 })
  void testIsBitSet_CaseSecondtBit_ShouldBeTrue(final byte byteValue) {
    // act
    final boolean bitSet = ByteUtil.isBitSet(byteValue, 1);
    // assert
    BDDAssertions.then(bitSet).isTrue();
  }

  @ParameterizedTest
  @ValueSource(bytes = { 1, 4, 5, 8, 9, 12, 13 })
  void testIsBitSet_CaseSecondBit_ShouldBeFalse(final byte byteValue) {
    // act
    final boolean bitSet = ByteUtil.isBitSet(byteValue, 1);
    // assert
    BDDAssertions.then(bitSet).isFalse();
  }

  @Test
  void testShiftLeft() {
    // arrange
    final byte[] input = new byte[] { 14, 8, 7, 1, 0 };

    // act
    final byte[] shiftLeft = ByteUtil.shiftLeft(input);

    // assert
    BDDAssertions.then(shiftLeft).isEqualTo(new byte[] { 28, 16, 14, 2, 0 });
  }

  @Test
  void testXORBits() {
    // arrange
    final byte[] input = new byte[] { 0x06 }; // 0110
    final byte[] maske = new byte[] { 0x03 }; // 0011

    // act
    final byte[] xorBits = ByteUtil.xorBits(input, maske);

    // assert
    BDDAssertions.then(xorBits).isEqualTo(new byte[] { 0x05 }); // 0101
  }
}
