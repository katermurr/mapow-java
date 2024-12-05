package eu.mapow.java09;

import java.lang.ProcessHandle.Info;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author mapow
 * @since 2024-12-02
 */
class ProcessApiTest {

    @Test
    void testPrintAll() {
        final Info current = ProcessApi.printAll();
        BDDAssertions.then(current.toString()).contains("java");
    }
}
