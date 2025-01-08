package eu.mapow.java.kryptografie.asymetric;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-03
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class KeyPairGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(KeyPairGenerator.class);

  static KeyPair generateKeyPair(final Algorithm algorithm) throws NoSuchAlgorithmException {
    final java.security.KeyPairGenerator keyGen = java.security.KeyPairGenerator.getInstance(algorithm.toString());
    keyGen.initialize(algorithm.getBits()); // key size in bits
    final KeyPair keyPair = keyGen.generateKeyPair();
    LOG.debug("Public Key: {}", Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
    LOG.debug("Private Key: {}", Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
    return keyPair;
  }
}
