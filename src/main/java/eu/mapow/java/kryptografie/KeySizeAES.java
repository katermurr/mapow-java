package eu.mapow.java.kryptografie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-02
 */
@RequiredArgsConstructor
public enum KeySizeAES {

  /** AES-128: 128 Bit Schlüssel (10 Runden) */
  BITS_128(128),

  /** AES-192: 192 Bit Schlüssel (12 Runden) */
  BITS_192(192),

  /** AES-256: 192 Bit Schlüssel (14 Runden) */
  BITS_256(256);

  @Getter
  private final int bit;

}
