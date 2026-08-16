package network.oxalis.vefa.validator.api;

import network.oxalis.vefa.validator.lang.ValidatorException;

import java.io.InputStream;

public interface DeclarationWithChildren extends Declaration {

    Iterable<CachedFile> children(InputStream inputStream) throws ValidatorException;

}
