package network.oxalis.vefa.validator;

import com.google.common.cache.CacheLoader;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import network.oxalis.vefa.validator.annotation.Type;
import network.oxalis.vefa.validator.api.Renderer;
import network.oxalis.vefa.validator.api.RendererFactory;
import network.oxalis.vefa.validator.lang.ValidatorException;
import no.difi.xsd.vefa.validator._1.StylesheetType;

import java.util.List;

/**
 * Pool of prepared renderers. Size if configured using properties.
 */
@Slf4j
@Singleton
public class RendererCacheLoader extends CacheLoader<String, Renderer> {

    public static final int DEFAULT_SIZE = 250;

    @Inject
    private ValidatorEngine validatorEngine;

    @Inject
    private List<RendererFactory> factories;

    @Override
    public Renderer load(String key) throws Exception {
        try {
            StylesheetType stylesheetType = validatorEngine.getStylesheet(key);

            for (RendererFactory factory : factories) {
                for (String extension : factory.getClass().getAnnotation(Type.class).value()) {
                    if (stylesheetType.getPath().toLowerCase().endsWith(extension)) {
                        log.debug("Renderer '{}'", key);
                        return factory.prepare(stylesheetType, validatorEngine.getResource(stylesheetType.getPath()), stylesheetType.getPath().split("#")[1]);
                    }
                }
            }
        } catch (Exception e) {
            throw new ValidatorException(String.format("Unable to load presenter for '%s'.", key), e);
        }

        throw new ValidatorException(String.format("No presenter found for '%s'", key));
    }
}
