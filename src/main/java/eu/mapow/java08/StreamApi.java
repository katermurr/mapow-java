package eu.mapow.java08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * https://howtodoinjava.com/java/stream/java-streams-by-examples/
 *
 * @author mapow
 * @since 2024-12-02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamApi {

    private static final Logger LOG = LoggerFactory.getLogger(StreamApi.class);

    static final List<String> NAMES;
    static {
        NAMES = new ArrayList<>();
        NAMES.add(null);
        NAMES.add("Amitabh");
        NAMES.add("Shekhar");
        NAMES.add("Aman");
        NAMES.add("Rahul");
        NAMES.add(null);
        NAMES.add("Shahrukh");
        NAMES.add("Salman");
        NAMES.add("Yana");
        NAMES.add("Lokesh");
    }

    // =================================================================================================================
    // Intermediate Operations
    // filter() map() flatMap() distinct() sorted() peek() limit() skip()
    // =================================================================================================================

    public static List<String> filter(final String prefix) {
        // with foreach
        final List<String> mappedList = new ArrayList<>();
        for (final String name : NAMES) {
            if (name != null && name.startsWith(prefix)) {
                mappedList.add(name);
            }
        }
        LOG.debug("mappedList: {}", mappedList);

        // with Stream API
        return NAMES.stream().filter(name -> (name != null && name.startsWith(prefix))).toList();
    }

    public static List<String> map(final String prefix) {
        return NAMES.stream().filter(name -> name != null && name.startsWith(prefix)).map(String::toUpperCase).toList();
    }

    public static List<String> flatMap(final List<String> list) {
        final List<List<String>> listOfLists = Arrays.asList(list, NAMES);
        return listOfLists.stream().flatMap(Collection::stream).toList();
    }

    public static List<String> sort() {
        return NAMES.stream().filter(Objects::nonNull).sorted().toList();
    }

    public static List<String> limit(final int limit) {
        return NAMES.stream().limit(limit).toList();
    }

    /*
     * Parallel Streams
     */
}
