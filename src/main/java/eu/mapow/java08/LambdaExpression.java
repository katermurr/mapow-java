package eu.mapow.java08;

import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * https://howtodoinjava.com/java/stream/functional-interface-tutorial/<br>
 * https://howtodoinjava.com/java8/lambda-expressions/<br>
 *
 * @author mapow
 * @since 2024-12-02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LambdaExpression {

  public static String lambdaParameter(final Supplier<String> supplier,
                                       final IntUnaryOperator square,
                                       final Consumer<String> consumer) {
    consumer.accept("lambda");
    return supplier.get() + " - " + square.applyAsInt(3);
  }
}