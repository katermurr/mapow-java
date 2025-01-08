package eu.mapow.java.kryptografie.asymetric;

import java.security.KeyPair;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2025-01-03
 */
class KeyPairGeneratorTest {

  @Test
  void testGenerateKeyPair_CaseRSA() throws Exception {
    // arrange
    final Algorithm algorithm = Algorithm.RSA;

    // act
    final KeyPair keyPair = KeyPairGenerator.generateKeyPair(algorithm);

    // assert
    BDDAssertions.then(keyPair.getPrivate().getAlgorithm()).isEqualTo(algorithm.toString());
    BDDAssertions.then(keyPair.getPrivate().getFormat()).isEqualTo(algorithm.getPrivateKeyEncoding());
    BDDAssertions.then(keyPair.getPrivate().getEncoded()).hasSizeGreaterThan(1000);

    BDDAssertions.then(keyPair.getPublic().getAlgorithm()).isEqualTo(algorithm.toString());
    BDDAssertions.then(keyPair.getPublic().getFormat()).isEqualTo(algorithm.getPublicKeyEncoding());
    BDDAssertions.then(keyPair.getPublic().getEncoded()).hasSizeGreaterThan(200);
  }

}
