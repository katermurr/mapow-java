package eu.mapow.java.kryptografie.asymetric;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-03
 */
@Getter
@RequiredArgsConstructor
public enum Algorithm {

  RSA("RSA", 2048, "PKCS#8", "X.509");

  private final String name;
  private final int bits;
  private final String privateKeyEncoding;
  private final String publicKeyEncoding;

  @Override
  public String toString() {
    return name;
  }

}
