package network.oxalis.vefa.validator;

import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import network.oxalis.vefa.validator.api.Properties;
import network.oxalis.vefa.validator.api.Source;
import network.oxalis.vefa.validator.module.PropertiesModule;
import network.oxalis.vefa.validator.module.SourceModule;
import network.oxalis.vefa.validator.module.ValidatorModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder supporting creation of validator.
 */
public class ValidatorBuilder {

    private Source source;

    private Properties properties;

    /**
     * Initiate creation of a new validator. Loads default plugins.
     *
     * @return Builder object
     */
    public static ValidatorBuilder newValidator() {
        return new ValidatorBuilder();
    }

    /**
     * Internal constructor, no action needed.
     */
    private ValidatorBuilder() {
        // No action
    }

    /**
     * Defines configuration to use for validator.
     *
     * @param properties Configuration
     * @return Builder object
     */
    public ValidatorBuilder setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Define source to use if other source then production repository to be used.
     *
     * @param source Source giving access to validation rules.
     * @return Builder object
     */
    public ValidatorBuilder setSource(Source source) {
        this.source = source;
        return this;
    }

    /**
     * Initiate validator and return validator ready for use.
     *
     * @return Validator ready for use.
     */
    public Validator build() {
        List<Module> modules = new ArrayList<>();
        modules.add(new PropertiesModule(properties));
        modules.add(new SourceModule(source));

        return Guice.createInjector(Modules.override(new ValidatorModule()).with(modules)).getInstance(Validator.class);
    }
}
