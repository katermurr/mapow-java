package eu.mapow.java.kryptografie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The following algorithm names can be specified when requesting an instance of KeyGenerator.
 *
 * (These classes generate keys for which Key.getAlgorithm() returns the standard algorithm name.)
 *
 * https://docs.oracle.com/en/java/javase/21/docs/specs/security/standard-names.html#keygenerator-algorithms
 *
 * @author mapow
 * @since 2025-01-02
 */
@Getter
@RequiredArgsConstructor
public enum Algorithm {

  /** The standard name for the AES algorithm. */
  AES("AES", 16, "AES/CBC/PKCS5Padding"),

  CHACHA20("ChaCha20", 12, "ChaCha20-Poly1305");

  private final String name;

  /** block size */
  private final int bytes;
  private final String transformation;

  @Override
  public String toString() {
    return name;
  }

}
