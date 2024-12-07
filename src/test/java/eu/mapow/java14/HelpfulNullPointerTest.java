package eu.mapow.java14;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-12-01
 */
class HelpfulNullPointerTest {

  /**
   * The traditional error message has been like this: Exception in thread "main" java.lang.NullPointerException at
   * de.mapow.java14.HelpfulNullPointer.throwNullPointer(HelpfulNullPointer.java:23)
   */
  @Test
  void testThrowNullPointer() {
    // act
    final NullPointerException cause = Assertions.assertThrows(NullPointerException.class,
        () -> HelpfulNullPointer.throwNullPointer(null));

    // assert
    BDDAssertions.then(cause).hasMessage("Cannot invoke \"String.toUpperCase()\" because \"arg\" is null");
  }

  @Test
  void testThrowNullPointer_ShouldReturnUpperCaseString() {
    BDDAssertions.then(HelpfulNullPointer.throwNullPointer("abc")).isEqualTo("ABC");
  }

}
