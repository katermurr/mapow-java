package eu.mapow.java.bits;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2025-01-19
 */
class BitOperationTest {

  @Test
  void testBitOperations() {
    BDDAssertions.then(8 & 1).isEqualTo(0); // 1000 & 0001 => 0000
    BDDAssertions.then(21 & 1).isEqualTo(1); // 11001 & 00001 => 00001

    BDDAssertions.then(8 | 1).isEqualTo(9); // 1000 | 0001 => 1001
    BDDAssertions.then(21 | 1).isEqualTo(21); // 11001 | 00001 => 11001

    BDDAssertions.then(8 ^ 1).isEqualTo(9); // 1000 ^ 0001 => 1001
    BDDAssertions.then(21 ^ 1).isEqualTo(20); // 11001 ^ 00001 => 11000

    BDDAssertions.then(~8).isEqualTo(0b11111111111111111111111111110111).isEqualTo(-9);
    BDDAssertions.then(~9).isEqualTo(0b11111111111111111111111111110110).isEqualTo(-10);
    BDDAssertions.then(~255).isEqualTo(-256);

    BDDAssertions.then(8 << 1).isEqualTo(16); // left shift 1000 => 10000
    BDDAssertions.then(21 << 1).isEqualTo(42); // left shift 10101 => 101010
  }

  @Test
  void testSetBit_CaseBit2() {
    // arrange
    final int value = 0b0100; // 4
    final int mask_ = 0b0010; // 2

    // act
    final int result = value | mask_;

    // assert
    BDDAssertions.then(result).isEqualTo(0b0110).isEqualTo(6);
  }

  @Test
  void testDeleteBit_CaseBit2() {
    // arrange
    final int value = 7; // 7 (0111)
    final int mask = ~2; // ~2 ist binär: 1101 - 2 ist binär: 0010

    // act
    final int result = value & mask;

    // assert
    BDDAssertions.then(result).isEqualTo(0b0101).isEqualTo(5);
  }

  @Test
  void testCheckBit_CaseBit2_ShouldBeSet() {
    // arrange
    final int value = 0b0110; // 6
    final int mask_ = 0b0010; // 2

    // act
    final boolean isSet = (value & mask_) != 0; // true, da Bit gesetzt

    // assert
    BDDAssertions.then(isSet).isTrue();
  }

  @Test
  void testCheckBit_CaseBit2_ShouldNotBeSet() {
    // arrange
    final int value = 0b0101; // 5
    final int mask_ = 0b0010; // 2
    // act
    final boolean isSet = (value & mask_) != 0;

    // assert
    BDDAssertions.then(isSet).isFalse();
  }

  @Test
  void testSwitchBit_CaseSwitchBit2To0() {
    // arrange
    final int value = 0b0110; // 6
    final int mask_ = 0b0010; // 2

    // act
    final int result = value ^ mask_;

    // assert
    BDDAssertions.then(result).isEqualTo(4).isEqualTo(0b0100);
  }
}
