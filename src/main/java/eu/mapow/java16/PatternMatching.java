package eu.mapow.java16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * https://openjdk.org/jeps/394<br>
 *
 *
 * @author mapow
 * @since 2024-12-01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatternMatching {

    private static final Logger LOG = LoggerFactory.getLogger(PatternMatching.class);

    /**
     * Syntax before Java 16 requires Cast:
     *
     * <pre>{@code
     * if (obj instanceof String) {
     *     String s = (String) obj;
     *     // use s
     * }
     * }</pre>
     */
    public static String checkInstanceOf(final Object object) {
        final String clazz = object.getClass().getName();
        if (object instanceof final String string) {
            final int length = string.length();
            LOG.debug("{} - {}", clazz, length);
        }
        if (object instanceof final Double number) {
            final byte byteValue = number.byteValue();
            LOG.debug("{} - {}", clazz, byteValue);
        }
        return clazz + " - " + object.toString();
    }
}
