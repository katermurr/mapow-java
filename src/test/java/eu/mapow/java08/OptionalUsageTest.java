package eu.mapow.java08;

import java.util.Optional;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-12-02
 */
class OptionalUsageTest {

  @Test
  void testGetOptional_CaseEmpty() {
    // act
    final Optional<Integer> optional = OptionalUsage.getOptional();

    // assert
    BDDAssertions.then(optional).isNotNull();
    BDDAssertions.then(optional.isPresent()).isFalse();
  }

  @Test
  void testGetOptional_CaseOrElse() {
    // act
    final Integer integer = OptionalUsage.getOptional().orElse(1); // return a new instance if optional is empty

    // assert
    BDDAssertions.then(integer).isEqualTo(1);
  }

  @Test
  void testGetOptional_CaseOfNullable() {
    // arrange
    final Optional<Integer> optional = OptionalUsage.getOptional();
    BDDAssertions.then(optional.isPresent()).isFalse();

    // act
    final Optional<Integer> optionalNullable = Optional.ofNullable(optional.orElse(1));

    // assert
    BDDAssertions.then(optionalNullable.isPresent()).isTrue();
    BDDAssertions.then(optionalNullable.get()).isEqualTo(1);
  }

  @Test
  void testGetOptional_CaseOrElseThrow() {
    // arrange
    final Optional<Integer> optional = OptionalUsage.getOptional();

    // act
    final IllegalStateException illegalStateException = Assertions.assertThrows(IllegalStateException.class,
        () -> optional.orElseThrow(IllegalStateException::new)); // throw an exception if optional is empty

    // assert
    BDDAssertions.then(illegalStateException).hasMessage(null);
  }
}
