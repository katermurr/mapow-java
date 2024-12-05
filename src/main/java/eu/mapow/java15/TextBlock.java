package eu.mapow.java15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * https://openjdk.org/jeps/378<br>
 * https://howtodoinjava.com/java14/java-text-blocks
 *
 * @author mapow
 * @since 2024-11-30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextBlock {

    private static final Logger LOG = LoggerFactory.getLogger(TextBlock.class);

    @SuppressWarnings("squid:S6126")
    public static String query() {
        final String query = "SELECT `EMP_ID`, `LAST_NAME`, `FIRST_NAME` FROM `EMPLOYEE_TB`\n"
                + "WHERE `CITY` = 'INDIANAPOLIS'\n" + "ORDER BY `EMP_ID`, `LAST_NAME`, `FIRST_NAME`;\n";
        LOG.debug(query);
        return query;
    }

    public static String multilineQuery() {
        final String query = """
                SELECT `EMP_ID`, `LAST_NAME`, `FIRST_NAME` FROM `EMPLOYEE_TB`
                WHERE `CITY` = 'INDIANAPOLIS'
                ORDER BY `EMP_ID`, `LAST_NAME`, `FIRST_NAME`;
                """;
        LOG.debug(query);
        return query;
    }
}
