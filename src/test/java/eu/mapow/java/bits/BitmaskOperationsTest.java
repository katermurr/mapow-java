package eu.mapow.java.bits;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author mapow
 * @since 2025-01-10
 */
class BitmaskOperationsTest {

  @ParameterizedTest
  @CsvSource(value = { "0, 00000000", "1, 00000001", "5,00000101" })
  void testVisualizeSensors(final int state, final String expected) {
    // act
    final String visualizeSensors = BitmaskOperations.visualizeSensors(state);

    // assert
    BDDAssertions.then(visualizeSensors).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource(value = { "0, 00000000", "1, 00000001", "5,00000101" })
  void testVisualizeSensorsOptimized(final int state, final String expected) {
    // act
    final String visualizeSensors = BitmaskOperations.visualizeSensorsOptimized(state);

    // assert
    BDDAssertions.then(visualizeSensors).isEqualTo(expected);
  }

  @Test
  void testActivateSensor_CaseState0() {
    // arrange
    final int state = 0b0000_0000;
    // act
    final int result = BitmaskOperations.activateSensor(state, 3);

    // assert
    BDDAssertions.then(result).isEqualTo(0b0000_0100).isEqualTo(4);
  }

  @Test
  void testActivateSensor_CaseState1() {
    // arrange
    final int state = 0b0000_0001;
    // act
    final int result = BitmaskOperations.activateSensor(state, 3);

    // assert
    BDDAssertions.then(result).isEqualTo(0b0000_0101).isEqualTo(5);
  }

  @Test
  void testDeactivateSensor_CaseState255() {
    // arrange
    final int state = 0b1111_1111;
    // act
    final int result = BitmaskOperations.deactivateSensor(state, 3);

    // assert
    BDDAssertions.then(result).isEqualTo(0b1111_1011).isEqualTo(251);
  }

  @Test
  void testDeactivateSensor_CaseState190() {
    // arrange
    final int state = 0b1011_1110;
    // act
    final int result = BitmaskOperations.deactivateSensor(state, 5);

    // assert
    BDDAssertions.then(result).isEqualTo(0b1010_1110).isEqualTo(174);
  }

  @Test
  void testDeactivateSensor_CaseSensorIsDeactivated() {
    // arrange
    final int state = 0b1000_0001;
    // act
    final int result = BitmaskOperations.deactivateSensor(state, 3);

    // assert
    BDDAssertions.then(result).isEqualTo(state);
  }

  @Test
  void testIsSensorActive_CaseSensor4_ShouldBeActive() {
    // arrange
    final int state = 0b0000_1000;
    // act
    final boolean sensorActive = BitmaskOperations.isSensorActive(state, 4);

    // assert
    BDDAssertions.then(sensorActive).isTrue();
  }

  @Test
  void testIsSensorActive_CaseSensor4_ShouldNotBeActive() {
    // arrange
    final int state = 0b0000_0111;
    // act
    final boolean sensorActive = BitmaskOperations.isSensorActive(state, 4);

    // assert
    BDDAssertions.then(sensorActive).isFalse();
  }

  @Test
  void testToggleSensor_CaseSensor5_ShouldBeActivated() {
    // arrange
    final int state = 0b0000_0001;

    // act
    final int result = BitmaskOperations.toggleSensor(state, 5);

    // assert
    BDDAssertions.then(result).isEqualTo(0b0001_0001).isEqualTo(17);
  }

  @Test
  void testToggleSensor_CaseSensor5_ShouldBeDeactivated() {
    // arrange
    final int state = 0b1001_0001;

    // act
    final int result = BitmaskOperations.toggleSensor(state, 5);

    // assert
    BDDAssertions.then(result).isEqualTo(0b1000_0001).isEqualTo(129);
  }

  @Test
  void testCountActiveSensor_ShouldBe3() {
    // arrange
    final int state = 0b1001_0001;

    // act
    final int activeSensors = BitmaskOperations.countActiveSensors(state);

    // assert
    BDDAssertions.then(activeSensors).isEqualTo(3);
  }

  @Test
  void testCountActiveSensor_ShouldBe0() {
    // arrange
    final int state = 0b000_0000;

    // act
    final int activeSensors = BitmaskOperations.countActiveSensors(state);

    // assert
    BDDAssertions.then(activeSensors).isEqualTo(0);
  }

}