package eu.mapow.java.threads;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility for handling InterruptedException and have one place to ignore missing code coverage:
 * https://github.com/mockito/mockito/issues/3344
 *
 * @author mapow
 * @since 2024-12-03
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadSleep {

  private static final Logger LOG = LoggerFactory.getLogger(ThreadSleep.class);

  public static void sleepOneSecond() {
    try {
      Thread.sleep(Duration.ofSeconds(1));
    } catch (final InterruptedException cause) {
      LOG.error("thread interrupted", cause);
      Thread.currentThread().interrupt();
    }
  }
}
