package eu.mapow.java.kryptografie.symetric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-02
 */
@RequiredArgsConstructor
public class FileEnryption {

  private static final Logger LOG = LoggerFactory.getLogger(FileEnryption.class);
  private final Algorithm algorithm;
  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  /**
   * The initialization vector is generated before encryption with SecureRandom. The IV is saved at the beginning of the
   * encrypted file so that it can be used during decryption. Without the IV, the decryption cannot be carried out
   * correctly.
   *
   * @param inputFile  not null
   * @param outputFile not null
   * @param secretKey  not null
   * @throws Exception
   */
  public void encryptFile(final File inputFile, final File outputFile, final SecretKey secretKey) {
    LOG.debug("Encrypting the file: {}", inputFile);
    try {
      final Cipher cipher = Cipher.getInstance(algorithm.getTransformation());
      /*
       * Generate a random IV: IV in AES modes such as CBC: The cipher mode AES/CBC/PKCS5Padding requires an IV
       * (initialization vector). If no IV has been explicitly set and cipher.getIV() is called, it returns null.
       */
      final byte[] initVectorBytes = new byte[algorithm.getBytes()];
      SECURE_RANDOM.nextBytes(initVectorBytes);
      final IvParameterSpec initVector = new IvParameterSpec(initVectorBytes);

      cipher.init(Cipher.ENCRYPT_MODE, secretKey, initVector);

      try (FileInputStream fis = new FileInputStream(inputFile);
          FileOutputStream fos = new FileOutputStream(outputFile)) {
        fos.write(initVectorBytes); // Write the IV at the beginning of the output file

        // Encrypt the file content
        final byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
          final byte[] encrypted = cipher.update(buffer, 0, bytesRead);
          fos.write(encrypted);
        }
        // Finalize encryption
        final byte[] finalBytes = cipher.doFinal();
        fos.write(finalBytes);

      }
    } catch (final Exception cause) {
      throw new SecurityException("Failed to encrypt file: " + inputFile.getName(), cause);
    }
    LOG.debug("File {} encrypted successfully to: {}", inputFile.getName(), outputFile.getAbsolutePath());
  }

  public void decryptFile(final File encryptedFile, final File outputFile, final SecretKey secretKey) {
    LOG.debug("Decrypting the file: {}", encryptedFile);
    try {
      final Cipher cipher = Cipher.getInstance(algorithm.getTransformation());
      try (FileInputStream fis = new FileInputStream(encryptedFile);
          FileOutputStream fos = new FileOutputStream(outputFile)) {

        // extract the IV from the encrypted file and initialize the same cipher with this IV
        final byte[] iv = new byte[algorithm.getBytes()];
        final int i = fis.read(iv); // // Read the IV from the beginning of the file
        LOG.debug("initialization vector: {}", i);
        final IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Decrypt the file content
        final byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
          final byte[] decrypted = cipher.update(buffer, 0, bytesRead);
          fos.write(decrypted);
        }
        // Finalize decryption
        final byte[] finalBytes = cipher.doFinal();
        fos.write(finalBytes);
      }
    } catch (final Exception cause) {
      throw new SecurityException("Failed to encrypt file: " + encryptedFile.getName(), cause);
    }
    LOG.debug("File {} decrypted successfully to: {}", encryptedFile.getName(), outputFile.getAbsolutePath());
  }
}
