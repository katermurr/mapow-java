package de.mapow.java10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * https://openjdk.org/jeps/286<br>
 * https://howtodoinjava.com/java10/var-local-variable-type-inference/
 *
 * @author mapow
 * @since 2024-12-01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalVariable {

    private static final Logger LOG = LoggerFactory.getLogger(LocalVariable.class);

    @SuppressWarnings("java:S125")

    // public var demo = "hello"; // error: 'var' is not allowed here

    // public Application(var param){ } //Not allowed as parameter

    // try{ } catch(var ex){ } //Not allowed as catch formal

    // public var demoMethod(){ return null; } //Not allowed in method return type

    // public Integer demoMethod2( var input ){ return null; } //Not allowed in method parameters

    /**
     * Bad usage: var and List<> without type! {code var list = new ArrayList<>();}
     *
     */
    public static String demoLocalVars() {

        final var message = "Hello World";

        final var list = new ArrayList<String>();
        list.add(message);

        final var idToNameMap = new HashMap<Integer, String>();
        idToNameMap.put(1, message);

        final List<String> data = Arrays.asList("a", "b");
        for (final var object : data) { // index in for-loop
            LOG.debug(object);
        }

        // var demo; // error: cannot use 'var' on variable without initializer

        // var emptyList = null; // error: variable initializer is 'null'

        // var string = (String s) -> s.length() > 10; // error: lambda expression needs
        // an explicit target-type

        // var arr = { 1, 2, 3 }; // error: array initializer needs an explicit target-type

        LOG.debug(message);
        return message;
    }
}
