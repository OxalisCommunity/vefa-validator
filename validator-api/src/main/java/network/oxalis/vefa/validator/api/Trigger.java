package network.oxalis.vefa.validator.api;

import network.oxalis.vefa.validator.lang.ValidatorException;

public interface Trigger {

    void check(Document document, Section section) throws ValidatorException;
    
}
