package network.oxalis.vefa.validator.source;

import network.oxalis.vefa.validator.api.Properties;
import network.oxalis.vefa.validator.api.SourceInstance;
import network.oxalis.vefa.validator.lang.ValidatorException;

import java.nio.file.Path;

/**
 * Defines a directories as source for validation artifacts.
 */
public class DirectorySource extends AbstractSource {

    private Path[] directories;

    /**
     * Initiate the new source.
     *
     * @param directories Directories containing validation artifacts.
     */
    public DirectorySource(Path... directories) {
        this.directories = directories;
    }

    @Override
    public SourceInstance createInstance(Properties properties) throws ValidatorException {
        return new DirectorySourceInstance(properties, directories);
    }
}
