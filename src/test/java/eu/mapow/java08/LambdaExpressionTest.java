package eu.mapow.java08;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mapow
 * @since 2024-12-02
 */
class LambdaExpressionTest {

  private static final Logger LOG = LoggerFactory.getLogger(LambdaExpressionTest.class);

  @Test
  void testSupplier() {
    // arrange
    final Supplier<String> supplier = () -> {
      LOG.debug("start supplier");
      final HttpClient httpClientMock = Mockito.mock(HttpClient.class);
      final Version http2 = Version.HTTP_2;
      BDDMockito.given(httpClientMock.version()).willReturn(http2);
      return http2.name();
    };
    final Consumer<String> consumer = arg -> LOG.debug("consumer {}", arg);

    // act
    final String showUsage = LambdaExpression.lambdaParameter(supplier, x -> x * x, consumer);

    // assert
    BDDAssertions.then(showUsage).isEqualTo(Version.HTTP_2.name() + " - 9");
  }

}
