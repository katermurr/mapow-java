package eu.mapow.java08;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mapow
 * @since 2024-12-02
 */
class StreamApiTest {

  private static final Logger LOG = LoggerFactory.getLogger(StreamApiTest.class);

  // =================================================================================================================
  // Creating Streams
  // concat() empty() generate() iterate() of()
  // =================================================================================================================

  @Test
  void testStreamOf_ShouldThrowIllegalStateException() {
    // arrange
    final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    stream.findAny();

    // act
    final IllegalStateException illegalStateException = Assertions.assertThrows(IllegalStateException.class,
        () -> stream.toList());
    // assert
    BDDAssertions.then(illegalStateException).hasMessage("stream has already been operated upon or closed");
  }

  /**
   * Invoking the functional method get() on the Supplier returns a freshly created Stream object, on which we can
   * safely perform another Stream operation.
   */
  @Test
  void testStreamOf_CaseWrapWithSupplier() {
    // arrange
    final Supplier<Stream<Integer>> streamSupplier = () -> Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    streamSupplier.get().findAny();

    // act
    final List<Integer> list = streamSupplier.get().toList();

    // assert
    BDDAssertions.then(list).containsExactlyElementsOf(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
  }

  @Test
  void testGenerate() {
    // arrange
    final Supplier<Stream<Integer>> streamSupplier = () -> Stream.generate(() -> (new Random()).nextInt(100));
    final Stream<Integer> randomNumbers = streamSupplier.get().limit(20);

    // act
    randomNumbers.forEach(integer -> LOG.debug("{}", integer));

    // assert
    BDDAssertions.then(streamSupplier.get().limit(20).count()).isEqualTo(20L);

  }

  @Test
  void testConcat() {
    // arrange
    final Stream<Integer> stream = Stream.of(1, 2);

    // act
    final Stream<Integer> concat = Stream.concat(stream, Stream.of(0));

    // assert
    BDDAssertions.then(concat).containsExactly(1, 2, 0);
  }

  // =================================================================================================================
  // Intermediate Operations
  // filter() map() flatMap() distinct() sorted() peek() limit() skip()
  // =================================================================================================================

  @Test
  void testFilter() {
    // arrange
    final String startsWith = "A";

    // act
    final List<String> filtered = StreamApi.filter(startsWith);

    // assert
    BDDAssertions.then(filtered).containsExactly("Amitabh", "Aman");
  }

  @Test
  void testMap() {
    // arrange
    final String startsWith = "A";

    // act
    final List<String> mapped = StreamApi.map(startsWith);

    // assert
    BDDAssertions.then(mapped).containsExactly("AMITABH", "AMAN");
  }

  @Test
  void testFlatMap() {
    // arrange
    final List<String> list = Arrays.asList("a", "b");

    // act
    final List<String> flatMap = StreamApi.flatMap(list);

    // assert
    BDDAssertions.then(flatMap).containsAll(StreamApi.NAMES);
    BDDAssertions.then(flatMap).containsAll(list);
  }

  @Test
  void testSort() {
    // act
    final List<String> sorted = StreamApi.sort();

    // assert
    BDDAssertions.then(sorted.getFirst()).isEqualTo("Aman");
    BDDAssertions.then(sorted.getLast()).isEqualTo("Yana");
  }

  @Test
  void testLimit() {
    // arrange
    final int limit = 5;

    // act
    final List<String> limited = StreamApi.limit(limit);

    // assert
    BDDAssertions.then(limited).hasSize(limit);
    BDDAssertions.then(limited.getFirst()).isNull();
    BDDAssertions.then(limited.getLast()).isEqualTo("Rahul");
  }

  // =================================================================================================================
  // Terminal operations
  // forEach() forEachOrdered() toArray() reduce() collect() min() max() count() anyMatch() allMatch() noneMatch()
  // findFirst() findAny()
  // =================================================================================================================

  @Test
  void testTerminalOperations() {
    final List<String> names = StreamApi.NAMES;

    names.stream().forEach(LOG::debug);
    names.stream().forEachOrdered(LOG::debug);

    BDDAssertions.then(names.stream().reduce((name1, name2) -> String.join("#", name1, name2)))
        .isEqualTo(Optional.of("null#Amitabh#Shekhar#Aman#Rahul#null#Shahrukh#Salman#Yana#Lokesh"));

    BDDAssertions.then(names.stream().filter(name -> name != null && name.startsWith("A")).count()).isEqualTo(2);

    BDDAssertions.then(names.stream().filter(Objects::nonNull).anyMatch(name -> name.startsWith("A"))).isTrue();
    BDDAssertions.then(names.stream().filter(Objects::nonNull).allMatch(name -> name.startsWith("A"))).isFalse();
    BDDAssertions.then(names.stream().filter(Objects::nonNull).noneMatch(name -> name.startsWith("A"))).isFalse();

    BDDAssertions.then(names.stream().filter(Objects::nonNull).findFirst()).isEqualTo(Optional.of("Amitabh"));
    BDDAssertions.then(names.stream().filter(Objects::nonNull).findAny()).isEqualTo(Optional.of("Amitabh"));
  }

}
