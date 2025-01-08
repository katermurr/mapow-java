package eu.mapow.java.kryptografie.symetric;

/**
 * @author menas
 * @since 2025-01-07
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PasswordHashing {

  private static final Logger LOG = LoggerFactory.getLogger(PasswordHashing.class);

  static String generateHash(final String password) throws Exception {
    final byte[] salt = generateRandomSalt();
    LOG.debug("Salt: {}", Base64.getEncoder().encodeToString(salt));

    final String hash = hashPasswortWithSalt(password, salt);
    LOG.debug("Hash: {}", hash);
    return hash;
  }

  static String generateHashWithoutSalt(final String password) throws NoSuchAlgorithmException {
    final String hash = hashPasswortWithSalt(password, null);
    LOG.debug("Hash: {}", hash);
    return hash;
  }

  private static byte[] generateRandomSalt() {
    final SecureRandom secureRandom = new SecureRandom();
    final byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }

  private static String hashPasswortWithSalt(final String password, final byte[] salt) throws NoSuchAlgorithmException {
    final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    if (salt != null) {
      messageDigest.update(salt);
    }
    final byte[] hashedPassword = messageDigest.digest(password.getBytes());
    return Base64.getEncoder().encodeToString(hashedPassword);
  }

  static String hashPwd(final String password) {
    final Argon2 argon2 = Argon2Factory.create();
    final String hash = argon2.hash(10, 65536, 5, password.toCharArray()); // Work-Faktor: 10 Iterationen
    LOG.debug("Hash: {}", hash);

    final boolean isMatch = argon2.verify(hash, password.toCharArray());
    LOG.debug("Password match: {}", isMatch);
    return hash;
  }
}
