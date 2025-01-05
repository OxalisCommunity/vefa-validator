package no.difi.vefa.validator;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import no.difi.vefa.validator.api.Validation;
import no.difi.xsd.vefa.validator._1.FlagType;

import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class ProfilingTest {

    private static Validator validator;

    @BeforeClass
    public static void beforeClass() throws Exception {
        validator = ValidatorBuilder.newValidator().build();
    }

    @Test
    @Ignore
    public void simple() throws Exception {
        for (int i = 0; i < 2000; i++) {
            try (InputStream inputStream = getClass().getResourceAsStream("/documents/huge-001.xml.gz")) {
                GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);

                Validation validation = validator.validate(gzipInputStream);
                assertEquals(FlagType.ERROR, validation.getReport().getFlag());

                gzipInputStream.close();
                inputStream.close();

                System.out.println(i);
            }
        }
    }
}
