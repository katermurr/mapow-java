package eu.mapow.java.kryptografie.hashing;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import eu.mapow.java.kryptografie.hashing.PasswordHashing;

/**
 * @author mapow
 * @since 2025-01-02
 */
class PasswordHashingTest {

  @Test
  void testHashPasswort_ShoulAddRandomSalt() throws Exception {
    // arrange
    final String pwd = "securePassword";

    // act
    final String hash = PasswordHashing.generateHash(pwd);

    // assert
    BDDAssertions.then(hash).isNotEqualTo(PasswordHashing.generateHash(pwd));
  }

  @Test
  void testHashPasswortWithoutSalt() throws Exception {
    // arrange
    final String pwd = "securePassword";

    // act
    final String hash = PasswordHashing.generateHashWithoutSalt(pwd);

    // assert
    BDDAssertions.then(hash).isEqualTo(PasswordHashing.generateHashWithoutSalt(pwd));
  }

  @Test
  void testHashPwd() {
    // arrange
    final String pwd = "securePassword";

    final String hash = PasswordHashing.hashPwd(pwd);

    // assert
    BDDAssertions.then(hash).isNotEqualTo(PasswordHashing.hashPwd(pwd));
  }
}
