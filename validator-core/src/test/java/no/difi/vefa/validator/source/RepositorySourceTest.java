package no.difi.vefa.validator.source;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertNotNull;

import no.difi.vefa.validator.api.Properties;
import no.difi.vefa.validator.lang.ValidatorException;

import java.net.URI;

public class RepositorySourceTest {

    // Dump test
    @Test
    public void simple() {
        assertNotNull(RepositorySource.forTest());
        assertNotNull(RepositorySource.forProduction());
    }

    @Test(expected = ValidatorException.class)
    public void triggerException() throws ValidatorException {
        RepositorySource source = new RepositorySource((URI) null);
        source.createInstance(Mockito.mock(Properties.class));
    }
}
