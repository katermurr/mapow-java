package de.mapow.java14;

import java.time.DayOfWeek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * https://openjdk.org/jeps/361
 *
 * @author mapow
 * @since 2024-11-30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SwitchExpression {

    private static final Logger LOG = LoggerFactory.getLogger(SwitchExpression.class);

    @SuppressWarnings({ "squid:S6208", "java:S1192" })
    public static boolean switchJava8(final String day) {
        boolean isWeekend;
        switch (day) {
        case "MONDAY":
        case "TUESDAY":
        case "WEDNESDAY":
        case "THURSDAY":
        case "FRIDAY":
            isWeekend = false;
            break;
        case "SATURDAY":
        case "SUNDAY":
            isWeekend = true;
            break;
        default:
            throw new IllegalArgumentException("What's a " + day);
        }
        LOG.debug("isWeekend: {}", isWeekend);
        return isWeekend;
    }

    public static boolean switchExpressionJava14(final String day) {
        final boolean isWeekend = switch (day) {
        case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> false;
        case "SATURDAY", "SUNDAY" -> true;
        default -> throw new IllegalArgumentException("What's a " + day);
        };
        LOG.debug("isWeekend: {}", isWeekend);
        return isWeekend;
    }

    /**
     * For switch expressions, the compiler insists that all possible cases are
     * covered. If we remove e.g. THURSDAY, we get a compile error, without the
     * default case: {@link #switchExpressionRequiredDefault(DayOfWeek)}.
     *
     */
    public static boolean switchExpressionWithoutDefault(final DayOfWeek dayOfWeek) {
        final boolean isWeekend = switch (dayOfWeek) {
        case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> false;
        case SATURDAY, SUNDAY -> true;
        };
        LOG.debug("isWeekend: {}", isWeekend);
        return isWeekend;
    }

    public static boolean switchExpressionRequiredDefault(final DayOfWeek dayOfWeek) {
        final boolean isWeekend = switch (dayOfWeek) {
        case MONDAY, TUESDAY, THURSDAY, FRIDAY -> false;
        case SATURDAY, SUNDAY -> true;
        default -> false;
        };
        LOG.debug("isWeekend: {}", isWeekend);
        return isWeekend;
    }

}
