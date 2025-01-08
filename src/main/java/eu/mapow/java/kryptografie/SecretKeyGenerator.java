package eu.mapow.java.kryptografie;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecretKeyGenerator {

  /**
   *
   * Creates a random secret key. This key is required for encryption and decryption.
   *
   * @param algorithm not null
   * @param keySize   >= 128
   * @return not null
   * @throws NoSuchAlgorithmException
   */
  public static SecretKey generateKey(final Algorithm algorithm, final int keySize) throws NoSuchAlgorithmException {
    final KeyGenerator keyGen = KeyGenerator.getInstance(algorithm.toString());
    keyGen.init(keySize, new SecureRandom());
    return keyGen.generateKey();
  }
}
