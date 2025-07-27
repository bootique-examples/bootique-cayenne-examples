package io.bootique.examples.cayenne;

import io.bootique.junit5.BQModuleTester;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void testAutoLoadable() {
        BQModuleTester.of(App.class).testAutoLoadable().testConfig();
    }
}
