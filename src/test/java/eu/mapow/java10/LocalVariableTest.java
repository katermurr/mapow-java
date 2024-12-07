package eu.mapow.java10;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-12-01
 */
class LocalVariableTest {

  @Test
  void tesDemoLocalVars() {
    BDDAssertions.then(LocalVariable.demoLocalVars()).isEqualTo("Hello World");
  }
}
