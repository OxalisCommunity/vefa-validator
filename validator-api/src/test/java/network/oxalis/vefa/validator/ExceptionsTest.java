package network.oxalis.vefa.validator;

import network.oxalis.vefa.validator.lang.UnknownDocumentTypeException;
import network.oxalis.vefa.validator.lang.ValidatorException;
import org.testng.annotations.Test;

public class ExceptionsTest {

    @Test(expectedExceptions = UnknownDocumentTypeException.class)
    public void unknownDocumentType() throws UnknownDocumentTypeException {
        throw new UnknownDocumentTypeException("test");
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validator1() throws ValidatorException {
        throw new ValidatorException("test");
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void validator2() throws ValidatorException {
        throw new ValidatorException("test", null);
    }
}
