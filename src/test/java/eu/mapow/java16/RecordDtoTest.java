package eu.mapow.java16;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-11-30
 */
class RecordDtoTest {

  @Test
  void testRecords() {
    // arrange
    final double latitude = 53.073635;
    final double longitude = 8.806422;

    // act
    final RecordDto recordDto = new RecordDto(latitude, longitude);

    // assert
    BDDAssertions.then(recordDto.latitude()).isEqualTo(latitude);
    BDDAssertions.then(recordDto.longitude()).isEqualTo(longitude);
  }

  @Test
  void testRecords_CaseToString() {
    // arrange
    final double latitude = 53.073635;
    final double longitude = 8.806422;

    // act
    final RecordDto recordDto = new RecordDto(latitude, longitude);

    // assert
    BDDAssertions.then(recordDto.toString()).isEqualTo("RecordDto[latitude=53.073635, longitude=8.806422]");
  }

  @Test
  void testRecords_CaseEqualsAndHashCode() {
    // arrange
    final double latitude = 53.073635;
    final double longitude = 8.806422;
    final RecordDto recordDto1 = new RecordDto(latitude, longitude);
    final RecordDto recordDto2 = new RecordDto(latitude, longitude);

    // act + assert
    BDDAssertions.then(recordDto1).isEqualTo(recordDto2);
  }
}
