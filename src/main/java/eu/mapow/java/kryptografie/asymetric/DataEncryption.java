package eu.mapow.java.kryptografie.asymetric;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-03
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DataEncryption {

  private static final Logger LOG = LoggerFactory.getLogger(DataEncryption.class);

  static String encrypt(final String message, final PublicKey publicKey) {
    try {
      final Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      final byte[] encryptedBytes = cipher.doFinal(message.getBytes());
      final String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
      LOG.debug("Encrypted Message: {}", encryptedMessage);
      return encryptedMessage;
    } catch (final Exception cause) {
      throw new SecurityException("Failed to encrypt message: " + message, cause);
    }
  }

  static String decrypt(final String encryptedMessage, final PrivateKey privateKey) {
    try {
      final Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      final byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
      final String decryptedMessage = new String(decryptedBytes);
      LOG.debug("Decrypted Message: {}", decryptedMessage);
      return decryptedMessage;
    } catch (final Exception cause) {
      throw new SecurityException("Failed to decrypt message: " + encryptedMessage, cause);
    }
  }
}
