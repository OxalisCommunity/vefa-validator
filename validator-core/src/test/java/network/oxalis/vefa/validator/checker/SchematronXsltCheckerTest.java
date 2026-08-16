package network.oxalis.vefa.validator.checker;

import network.oxalis.vefa.validator.lang.ValidatorException;
import org.testng.annotations.Test;

public class SchematronXsltCheckerTest {

    @Test(expectedExceptions = ValidatorException.class)
    public void simpleTriggerException() throws Exception {
        new SchematronXsltCheckerFactory().prepare(null, null);
    }
}
