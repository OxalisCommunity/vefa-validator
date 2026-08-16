package network.oxalis.vefa.validator.configuration;

import network.oxalis.vefa.validator.api.ConfigurationProvider;
import network.oxalis.vefa.validator.builder.ConfigurationBuilder;
import network.oxalis.vefa.validator.builder.ConfigurationsBuilder;
import no.difi.xsd.vefa.validator._1.Configurations;

public class AsiceConfigurationProvider implements ConfigurationProvider {

    @Override
    public Configurations getConfigurations() {
        return ConfigurationsBuilder
                .instance()
                // .pkg("ASiC-E")
                .configuration(ConfigurationBuilder
                        .identifier("asice-archive")
                        .title("ASiC-E")
                        .declaration("zip.asice", "application/vnd.etsi.asic-e+zip")
                        .trigger("asice")
                        .weight(Long.MIN_VALUE)
                        .build())
                .configuration(ConfigurationBuilder
                        .identifier("asice-archive-xml")
                        .title("ASiC-E")
                        .declaration("xml.asice", "application/vnd.etsi.asic-e+zip")
                        .weight(Long.MIN_VALUE)
                        .build())
                .build();
    }
}
