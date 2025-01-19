package eu.mapow.java.bits;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

  /**
   * Schreibe eine Funktion, die einen bestimmten Sensor (1–8) aktiviert (setzt das Bit auf 1).
   */
  @Test
  void testAktiviereSensor_CaseSensor3() {
    // arrange
    final int state = 0b1000_0000;
    final int mask_ = 0b0000_0100;
    final int expected = 0b1000_0100;

    // act
    final int result = state | mask_;

    // assert
    BDDAssertions.then(result).isEqualTo(expected).isEqualTo(state | (1 << (3 - 1)));
  }

  /**
   * Schreibe eine Funktion, die einen bestimmten Sensor deaktiviert (setzt das Bit auf 0).
   */
  @Test
  void testDeaktiviereSensor_CaseSensor5() {
    // arrange
    final int state = 0b1111_1111;
    final int mask_ = 0b1110_1111;

    // act
    final int result = state & mask_;

    // assert
    BDDAssertions.then(result).isEqualTo(0b1110_1111).isEqualTo(state & ~(1 << (5 - 1)));
  }

  /**
   * Schreibe eine Funktion, die prüft, ob ein bestimmter Sensor aktiv ist (Bit ist 1).
   */
  @ParameterizedTest
  @CsvSource(value = { "7,true", "4,false" })
  void testCheckSensor_CaseSensor2(final int state, final boolean expected) {
    // arrange
    final int mask = 0b0000_0010;

    // act
    final boolean isSet = (state & mask) != 0;

    // assert
    BDDAssertions.then(isSet).isEqualTo(expected);
  }

  /**
   * Schreibe eine Funktion, die den Status eines Sensors umschaltet (0 → 1 oder 1 → 0).
   */
  @ParameterizedTest
  @CsvSource(value = { "8,0", "15,7" })
  void testSwitchSensor_CaseSwitchSensor4(final int state, final int expected) {
    // arrange
    final int mask = 0b1000;

    // act
    final int result = state ^ mask;

    // assert
    BDDAssertions.then(result).isEqualTo(expected);
  }

  /**
   * Schreibe eine Funktion, die alle Sensoren deaktiviert (setzt alle Bits auf 0).
   */
  @Test
  void testDeaktiviereAlleSensoren() {
    final int state = 0b1111_1111;
    final int mask_ = 0b0000_0000;

    // act
    final int result = state & mask_;

    // assert
    BDDAssertions.then(result).isEqualTo(0);
  }

  /**
   * Schreibe eine Funktion, die zählt, wie viele Sensoren aktiv sind.
   */
  @ParameterizedTest
  @CsvSource(value = { "0,0", "3,2", "8,1", "31,5" })
  void testCountAktiveSensoren(final int state, final int expected) {
    // arrange
    final String binaryString = Integer.toBinaryString(state);

    // act
    final int result = binaryString.replaceAll("0", "").length();

    // assert
    BDDAssertions.then(result).isEqualTo(expected);
  }
}
