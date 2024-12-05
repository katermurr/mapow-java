package eu.mapow.java14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * https://openjdk.org/jeps/358<br>
 * https://howtodoinjava.com/java14/helpful-nullpointerexception/
 *
 * @author mapow
 * @since 2024-12-01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HelpfulNullPointer {

    private static final Logger LOG = LoggerFactory.getLogger(HelpfulNullPointer.class);

    public static String throwNullPointer(final String arg) {
        final String upperCase = arg.toUpperCase();
        LOG.debug("{}", upperCase);
        return upperCase;
    }

}
