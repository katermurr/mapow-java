package eu.mapow.java08;

/**
 * https://howtodoinjava.com/java/stream/functional-interface-tutorial/<br>
 * https://howtodoinjava.com/java8/lambda-expressions/<br>
 *
 * A lambda expression is an expression that represents an instance of a functional interface.<br>
 *
 * Apart from one (!) abstract method, a functional interface can also have the following methods that do not count for
 * defining it as a functional interface.
 *
 * <pre>
    - Default methods
    - Static methods
    - Public methods inherited from the Object class
 * </pre>
 *
 * Java’s most commonly used functional interfaces:
 *
 * <pre>
    - Runnable: contains only the run() method.
    - Comparable: contains only the compareTo() method.
    - ActionListener: contains only the actionPerformed() method.
    - Callable: contains only the call() method.
    - Predicate: a boolean-valued function that takes an argument and returns true or false.
    - BiPredicate: a predicate with two arguments.
    - Consumer: an operation that takes an argument, operates on it, and returns no result.
    - BiConsumer: a consumer with two arguments.
    - Supplier: a supplier that returns a value.
    - Function<T, R>:  takes an argument of type T and returns a result of type R.
    - BiFunction<T, U, R>: takes two arguments of types T and U and returns a result of type R.
 * </pre>
 *
 *
 *
 * @author mapow
 * @since 2024-12-02
 */
@FunctionalInterface
public interface FunctionalInterfaceUsage<T> {

  T process(T a, T b);

}
