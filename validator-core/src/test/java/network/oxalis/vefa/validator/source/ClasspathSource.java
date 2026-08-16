package network.oxalis.vefa.validator.source;

import network.oxalis.vefa.validator.api.Properties;
import network.oxalis.vefa.validator.api.SourceInstance;
import network.oxalis.vefa.validator.lang.ValidatorException;

public class ClasspathSource extends AbstractSource {

    private String location;

    public ClasspathSource(String location) {
        this.location = location;
    }

    @Override
    public SourceInstance createInstance(Properties properties) throws ValidatorException {
        return new ClasspathSourceInstance(properties, location);
    }
}
