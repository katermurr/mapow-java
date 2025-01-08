package eu.mapow.java.kryptografie.symetric;

import javax.crypto.SecretKey;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import eu.mapow.java.kryptografie.Algorithm;
import eu.mapow.java.kryptografie.symetric.Algorithm;
import eu.mapow.java.kryptografie.symetric.SecretKeyGenerator;

/**
 * @author mapow
 * @since 2025-01-02
 */
class SecretKeyGeneratorTest {

  @ParameterizedTest
  @CsvSource(value = { "128, 16", "192, 24", "256, 32" })
  void testGenerateKey_CaseAES(final int keySize, final int expectedByteLength) throws Exception {
    // arrange
    final Algorithm algorithm = Algorithm.AES;

    // act
    final SecretKey aesKey = SecretKeyGenerator.generateKey(algorithm, keySize);

    // assert
    BDDAssertions.then(aesKey.getAlgorithm()).isEqualTo(Algorithm.AES.toString());
    BDDAssertions.then(aesKey.getFormat()).isEqualTo("RAW");
    BDDAssertions.then(aesKey.getEncoded().length).isEqualTo(expectedByteLength);
  }

  @Test
  void testGenerateKey_CaseChaCha() throws Exception {
    // arrange
    final Algorithm algorithm = Algorithm.CHACHA20;
    final int keySize = 256;
    final int expectedByteLength = 32;

    // act
    final SecretKey aesKey = SecretKeyGenerator.generateKey(algorithm, keySize);

    // assert
    BDDAssertions.then(aesKey.getAlgorithm()).isEqualTo(Algorithm.CHACHA20.toString());
    BDDAssertions.then(aesKey.getFormat()).isEqualTo("RAW");
    BDDAssertions.then(aesKey.getEncoded().length).isEqualTo(expectedByteLength);
  }
}
