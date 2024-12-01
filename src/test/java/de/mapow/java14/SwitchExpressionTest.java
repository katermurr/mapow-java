package de.mapow.java14;

import java.time.DayOfWeek;
import java.time.Month;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

/**
 * @author mapow
 * @since 2024-11-30
 */
class SwitchExpressionTest {

    @Test
    void testSwitchExpression_CaseWeekay() {
        // arrange
        final String day = DayOfWeek.FRIDAY.name();

        // act
        final boolean switchJava8 = SwitchExpression.switchJava8(day);
        final boolean switchExpressionJava14 = SwitchExpression.switchExpressionJava14(day);

        // assert
        BDDAssertions.then(switchJava8).isEqualTo(switchExpressionJava14).isFalse();
    }

    @Test
    void testSwitchExpression_CaseWeekend() {
        // arrange
        final String day = DayOfWeek.SATURDAY.name();

        // act
        final boolean switchJava8 = SwitchExpression.switchJava8(day);
        final boolean switchExpressionJava14 = SwitchExpression.switchExpressionJava14(day);

        // assert
        BDDAssertions.then(switchJava8).isEqualTo(switchExpressionJava14).isTrue();
    }

    @Test
    void testSwitchExpression_CaseIllegalArgument() {
        // arrange
        final String day = Month.JULY.name();

        // act
        final IllegalArgumentException causeSwitchJava8 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> SwitchExpression.switchJava8(day));
        final IllegalArgumentException switchExpressionJava14 = Assertions.assertThrows(IllegalArgumentException.class,
                () -> SwitchExpression.switchExpressionJava14(day));

        // assert
        BDDAssertions.then(causeSwitchJava8.getMessage()).isEqualTo(switchExpressionJava14.getMessage())
                .isEqualTo("What's a JULY");
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, mode = Mode.EXCLUDE, names = { "SATURDAY", "SUNDAY" })
    void testSwitchExpression_CaseWithoutDefault_ShouldBeWeekday(final DayOfWeek day) {
        // act
        final boolean withoutDefault = SwitchExpression.switchExpressionWithoutDefault(day);
        final boolean withDefault = SwitchExpression.switchExpressionRequiredDefault(day);

        // assert
        BDDAssertions.then(withoutDefault).isEqualTo(withDefault).isFalse();
    }

    @Test
    void testSwitchExpression_CaseWithoutDefault_ShouldBeWeekend() {
        // arrange
        final DayOfWeek day = DayOfWeek.SATURDAY;

        // act
        final boolean withoutDefault = SwitchExpression.switchExpressionWithoutDefault(day);
        final boolean withDefault = SwitchExpression.switchExpressionRequiredDefault(day);

        // assert
        BDDAssertions.then(withoutDefault).isEqualTo(withDefault).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, mode = Mode.EXCLUDE, names = { "SATURDAY", "SUNDAY" })
    void testYieldWithMultiStatmentsInCaseBlock_ShouldBeWeekday(final DayOfWeek day) {
        // act
        final boolean isWeekEnd = SwitchExpression.yieldWithMultiStatmentsInCaseBlock(day);

        // assert
        BDDAssertions.then(isWeekEnd).isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, mode = Mode.INCLUDE, names = { "SATURDAY", "SUNDAY" })
    void testYieldWithMultiStatmentsInCaseBlock_ShouldBeWeekend(final DayOfWeek day) {
        // act
        final boolean isWeekEnd = SwitchExpression.yieldWithMultiStatmentsInCaseBlock(day);

        // assert
        BDDAssertions.then(isWeekEnd).isTrue();
    }

}
