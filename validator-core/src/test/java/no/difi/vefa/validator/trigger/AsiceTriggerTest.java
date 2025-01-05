package no.difi.vefa.validator.trigger;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import no.difi.vefa.validator.Validator;
import no.difi.vefa.validator.ValidatorBuilder;
import no.difi.vefa.validator.api.Validation;
import no.difi.vefa.validator.source.ClasspathSource;
import no.difi.xsd.vefa.validator._1.FlagType;

import java.io.IOException;
import java.io.InputStream;

public class AsiceTriggerTest {

    private static Validator validator;

    @BeforeClass
    public static void beforeClass() {
        validator = ValidatorBuilder.newValidator()
                .setSource(new ClasspathSource("/rules/"))
                .build();
    }

    @AfterClass
    public static void afterClass() {
        validator.close();
    }

    @Test
    @Ignore
    public void simpleInvalidAsice() {
        Validation validation = validator.validate(getClass().getResourceAsStream("/documents/asic-cades-test-invalid-signature.asice"));
        assertEquals(validation.getReport().getFlag(), FlagType.FATAL);
    }

    @Test
    public void simpleValidAsice() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/documents/asic-cades-test-valid.asice")) {
            Validation validation = validator.validate(inputStream);
            assertEquals(validation.getReport().getFlag(), FlagType.OK);
        }
    }
}