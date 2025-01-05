package no.difi.vefa.validator.build;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.inject.Inject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleProjectTest {

    @Inject
    private Cli cli;

    @Before
    public void before() {
        Cli.getInjector().injectMembers(this);
    }

    @Test
    public void simple() throws Exception {
        Path path = Paths.get(getClass().getResource("/project/simple").toURI());

        // Assert.assertFalse(Files.exists(path.resolve("target")));
        assertEquals(cli.perform(path.toString()), 0);
        assertTrue(Files.exists(path.resolve("target")));
    }

    @Test
    public void simpleWithTests() throws Exception {
        Path path = Paths.get(getClass().getResource("/project/simple").toURI());

        // Assert.assertFalse(Files.exists(path.resolve("target")));
        assertEquals(cli.perform("-test", "-x", path.toString()), 0);
        assertTrue(Files.exists(path.resolve("target")));
    }
}
