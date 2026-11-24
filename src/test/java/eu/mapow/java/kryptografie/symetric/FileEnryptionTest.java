package eu.mapow.java.kryptografie.symetric;

import java.io.File;
import java.nio.file.Files;

import javax.crypto.SecretKey;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2025-01-02
 */
class FileEnryptionTest {

  @Test
  void testEncryptAndDecrypt_CaseAES() throws Exception {
    // arrange
    final Algorithm algorithm = Algorithm.AES;
    final File inputFile = new File("src/test/resources/input.txt");
    final File encryptedFile = new File("target/encrypted_" + algorithm + ".dat");
    final File decryptedFile = new File("target/decrypted_" + algorithm + ".txt");

    final SecretKey secretKey = SecretKeyGenerator.generateKey(algorithm, KeySizeAES.BITS_256.getBit());
    final FileEnryption fileEnryption = new FileEnryption(algorithm);

    // act - encrypt
    fileEnryption.encryptFile(inputFile, encryptedFile, secretKey);

    // assert - encrypt
    BDDAssertions.then(encryptedFile.exists()).isTrue();

    // act - decrypt
    fileEnryption.decryptFile(encryptedFile, decryptedFile, secretKey);

    // assert - decrypt
    BDDAssertions.then(Files.readString(decryptedFile.toPath()))
                 .startsWith("""
                             Hallo,\r
                             \r
                             im Anhang dieser Mail finden Sie zwei Dateien: Ihren öffentlichen von uns beglaubigten OpenPGP-Schlüssel (signedPubKey.asc)""");
    BDDAssertions.then(Files.readAllLines(decryptedFile.toPath())).isEqualTo(Files.readAllLines(inputFile.toPath()));
  }

  @Test
  void testEncryptAndDecrypt_CaseChaCha() throws Exception {
    // arrange
    final Algorithm algorithm = Algorithm.CHACHA20;
    final File inputFile = new File("src/test/resources/input.txt");
    final File encryptedFile = new File("target/encrypted_" + algorithm + ".dat");
    final File decryptedFile = new File("target/decrypted_" + algorithm + ".txt");

    final SecretKey secretKey = SecretKeyGenerator.generateKey(algorithm, 256);
    final FileEnryption fileEnryption = new FileEnryption(algorithm);

    // act - encrypt
    fileEnryption.encryptFile(inputFile, encryptedFile, secretKey);

    // assert - encrypt
    BDDAssertions.then(encryptedFile.exists()).isTrue();

    // act - decrypt
    fileEnryption.decryptFile(encryptedFile, decryptedFile, secretKey);

    // assert - decrypt
    BDDAssertions.then(Files.readString(decryptedFile.toPath()))
                 .startsWith("""
                             Hallo,\r
                             \r
                             im Anhang dieser Mail finden Sie zwei Dateien: Ihren öffentlichen von uns beglaubigten OpenPGP-Schlüssel (signedPubKey.asc)""");
    BDDAssertions.then(Files.readAllLines(decryptedFile.toPath())).isEqualTo(Files.readAllLines(inputFile.toPath()));
  }

  @Test
  void testEncryptFile_ShouldThrowSecurityException() throws Exception {
    // arrange
    final SecretKey secretKey = SecretKeyGenerator.generateKey(Algorithm.AES, 256);
    final FileEnryption fileEnryption = new FileEnryption(null);
    final File inputFile = new File("src/test/resources/input.txt");
    final File ouputFile = new File("target/encrypted.dat");
    final String expectedMessage = "Failed to encrypt file: input.txt";
    final String expectedRootCauseMessage
      = "Cannot invoke \"eu.mapow.java.kryptografie.symetric.Algorithm.getTransformation()\" " +
        "because \"this.algorithm\" is null";

    // act
    final SecurityException expectedException
      = Assertions.assertThrows(SecurityException.class,
                                () -> fileEnryption.encryptFile(inputFile, ouputFile, secretKey));

    // assert
    BDDAssertions.then(expectedException).hasMessage(expectedMessage);
    BDDAssertions.then(expectedException.getCause()).hasMessage(expectedRootCauseMessage);
  }

  @Test
  void testDecryptFile_ShouldThrowSecurityException() throws Exception {
    // arrange
    final SecretKey secretKey = SecretKeyGenerator.generateKey(Algorithm.AES, 256);
    final FileEnryption fileEnryption = new FileEnryption(null);
    final File encryptedFile = new File("target/encryptedFile.dat");
    final File outputFile = new File("target/decrypted.txt");
    final String expectedMessage = "Failed to encrypt file: encryptedFile.dat";
    final String expectedRootCauseMessage
      = "Cannot invoke \"eu.mapow.java.kryptografie.symetric.Algorithm.getTransformation()\" " +
        "because \"this.algorithm\" is null";

    // act
    final SecurityException expectedException
      = Assertions.assertThrows(SecurityException.class,
                                () -> fileEnryption.decryptFile(encryptedFile, outputFile, secretKey));

    // assert
    BDDAssertions.then(expectedException).hasMessage(expectedMessage);
    BDDAssertions.then(expectedException.getCause()).hasMessage(expectedRootCauseMessage);
  }
}
