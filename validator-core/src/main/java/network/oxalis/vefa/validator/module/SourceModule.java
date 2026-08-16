package network.oxalis.vefa.validator.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import network.oxalis.vefa.validator.api.Properties;
import network.oxalis.vefa.validator.api.Source;
import network.oxalis.vefa.validator.api.SourceInstance;
import network.oxalis.vefa.validator.lang.ValidatorException;
import network.oxalis.vefa.validator.source.RepositorySource;

/**
 * @author erlend
 */
public class SourceModule extends AbstractModule {

    private final Source source;

    public SourceModule() {
        this(null);
    }

    public SourceModule(Source source) {
        this.source = source;
    }

    @Provides
    @Singleton
    public SourceInstance getSource(Properties properties) throws ValidatorException {
        // Make sure to default to repository source if no source is set.
        return (source != null ? source : RepositorySource.forProduction())
                .createInstance(properties);
    }
}
