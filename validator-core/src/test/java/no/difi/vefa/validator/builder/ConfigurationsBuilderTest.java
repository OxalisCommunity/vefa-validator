package no.difi.vefa.validator.builder;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import no.difi.xsd.vefa.validator._1.Configurations;

public class ConfigurationsBuilderTest {

    @Test
    public void simple() {
        Configurations cfg = ConfigurationsBuilder
                .instance()
                .configuration(ConfigurationBuilder.identifier("test").build())
                .pkg("Test")
                .build();

        assertEquals(cfg.getConfiguration().size(), 1);
        assertNull(cfg.getPackage().get(0).getUrl());
        assertEquals(cfg.getPackage().get(0).getValue(), "Test");
    }
}
