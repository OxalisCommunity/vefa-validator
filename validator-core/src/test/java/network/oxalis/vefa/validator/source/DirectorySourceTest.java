package network.oxalis.vefa.validator.source;

import network.oxalis.vefa.validator.api.Properties;
import network.oxalis.vefa.validator.lang.ValidatorException;
import org.mockito.Mockito;
import org.testng.annotations.Test;

public class DirectorySourceTest {

    @Test(expectedExceptions = ValidatorException.class)
    public void triggerException() throws ValidatorException {
        DirectorySource source = new DirectorySource(null);
        source.createInstance(Mockito.mock(Properties.class));
    }
}
