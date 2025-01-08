package eu.mapow.java.kryptografie.asymetric;

import java.security.KeyPair;
import java.security.PublicKey;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author mapow
 * @since 2025-01-03
 */
class DataEncryptionTest {

  @Test
  void testEncrypt_ShouldResultInDifferentResults() throws Exception {
    // arrange
    final KeyPair keyPair = KeyPairGenerator.generateKeyPair(Algorithm.RSA);
    final String message = "Hello RSA!";

    // act
    final String encrypt = DataEncryption.encrypt(message, keyPair.getPublic());

    // assert
    BDDAssertions.then(encrypt).isNotEqualTo(DataEncryption.encrypt(message, keyPair.getPublic()));
  }

  @Test
  void testEncrypt_ShouldThrowSecurityException() throws Exception {
    // arrange
    final String message = "Z2VoZWltZSBOYWNocmljaHQ=";
    final String expectedException = "Failed to encrypt message: Z2VoZWltZSBOYWNocmljaHQ=";

    // act
    final SecurityException securityException = Assertions.assertThrows(SecurityException.class,
        () -> DataEncryption.encrypt(message, Mockito.mock(PublicKey.class)));

    // assert
    BDDAssertions.then(securityException).hasMessage(expectedException);
    BDDAssertions.then(securityException.getCause()).hasMessage("Null or empty transformation");
  }

  @Test
  void testDecrypt_ShouldThrowSecurityException() throws Exception {
    // arrange
    final KeyPair keyPair = KeyPairGenerator.generateKeyPair(Algorithm.RSA);
    final String message = "Z2VoZWltZSBOYWNocmljaHQ=";
    final String expectedException = "Failed to decrypt message: Z2VoZWltZSBOYWNocmljaHQ=";

    // act
    final SecurityException securityException = Assertions.assertThrows(SecurityException.class,
        () -> DataEncryption.decrypt(message, keyPair.getPrivate()));

    // assert
    BDDAssertions.then(securityException).hasMessage(expectedException);
    BDDAssertions.then(securityException.getCause()).hasMessage("Padding error in decryption");
  }

  @Test
  void testEncryptAndDecryptMessage() throws Exception {
    // arrange
    final KeyPair keyPair = KeyPairGenerator.generateKeyPair(Algorithm.RSA);
    final String message = "Hello RSA!";

    // act
    final String encryptedMessage = DataEncryption.encrypt(message, keyPair.getPublic());
    final String decryptedMessage = DataEncryption.decrypt(encryptedMessage, keyPair.getPrivate());

    // assert
    BDDAssertions.then(decryptedMessage).isEqualTo(message);
  }
}
