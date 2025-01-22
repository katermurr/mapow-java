package eu.mapow.java.bits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author mapow
 * @since 2025-01-10
 */
@SuppressWarnings("java:S2629")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BitmaskOperations {

  private static final Logger LOG = LoggerFactory.getLogger(BitmaskOperations.class);

  // Aktiviert den angegebenen Sensor (1–8).
  public static int activateSensor(final int state, final int sensor) {
    LOG.debug("activateSensor: {}", sensor);
    LOG.debug(" {}", visualizeSensors(state));
    final int bitmask = 1 << sensor - 1;
    LOG.debug("|{}", visualizeSensors(bitmask));
    return state | bitmask;
  }

  // Deaktiviert den angegebenen Sensor (1–8).
  public static int deactivateSensor(final int state, final int sensor) {
    LOG.debug("deactivateSensor: {}", sensor);
    LOG.debug("                         {}", Integer.toBinaryString(state));
    final int bitmask = ~(1 << (sensor - 1));
    LOG.debug("&{}", Integer.toBinaryString(bitmask));
    return state & bitmask;
  }

  // Prüft, ob der angegebene Sensor aktiv ist (1–8).
  public static boolean isSensorActive(final int state, final int sensor) {
    LOG.debug("isSensorActive: {}", sensor);
    LOG.debug(" {}", visualizeSensors(state));
    final int bitmask = 1 << sensor - 1;
    LOG.debug("&{}", visualizeSensors(bitmask));
    LOG.debug(" result: {}", state & bitmask);
    return (state & bitmask) != 0;
  }

  // Schaltet den Status des angegebenen Sensors um (1–8).
  public static int toggleSensor(final int state, final int sensor) {
    LOG.debug("toggleSensor: {}", sensor);
    LOG.debug(" {}", visualizeSensors(state));
    final int bitmask = 1 << sensor - 1;
    LOG.debug("^{}", visualizeSensors(bitmask));
    return state ^ bitmask;
  }

  // Zählt die Anzahl der aktiven Sensoren.
  public static int countActiveSensors(int state) {
    LOG.debug("countActiveSensors: {}", state);
    int count = 0;
    while (state != 0) {
      LOG.debug(" {}", visualizeSensors(state));
      LOG.debug("&{}", visualizeSensors(1));
      count += state & 1;
      state >>= 1;
    }
    return count;
  }

  // Visualisiert den aktuellen Sensorstatus als Zeichenkette.
  public static String visualizeSensors(final int state) {
    final StringBuilder result = new StringBuilder();
    int rest = state;
    while (rest > 0) {
      result.append(rest % 2);
      rest /= 2;
    }
    int length = result.length();
    while (length < 8) {
      result.append("0");
      ++length;
    }
    return result.reverse().toString();
  }

  public static String visualizeSensorsOptimized(int state) {
    final StringBuilder result = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      result.append((state & 1) == 1 ? "1" : "0");
      state >>= 1;
    }
    return result.reverse().toString();
  }

}
