package network.oxalis.vefa.validator.api;

import network.oxalis.vefa.validator.lang.ValidatorException;

/**
 * @author erlend
 */
public interface CheckerFactory {

    Checker prepare(ArtifactHolder artifactHolder, String path) throws ValidatorException;

}
