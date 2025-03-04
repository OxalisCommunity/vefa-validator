package no.difi.vefa.validator.util;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.common.io.ByteStreams;
import com.google.inject.Guice;
import com.google.inject.Inject;
import no.difi.vefa.validator.module.ValidatorModule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DeclarationDetectorTest {

    @Inject
    private DeclarationDetector declarationDetector;

    @Before
    public void beforeClass() {
        Guice.createInjector(new ValidatorModule()).injectMembers(this);
    }

    @Test
    public void simple() throws Exception {
        //noinspection ConstantConditions
        byte[] bytes = ByteStreams.toByteArray(getClass().getResourceAsStream("/documents/ehf-invoice-2.0.xml"));

        InputStream inputStream = new ByteArrayInputStream(bytes);
        DeclarationIdentifier declarationIdentifier = declarationDetector.detect(inputStream);
        assertEquals(declarationIdentifier.getDeclaration().getType(), "xml.ubl");
        assertEquals(declarationIdentifier.getIdentifier().get(0), "urn:www.cenbii.eu:profile:bii05:ver2.0#urn:www.cenbii.eu:transaction:biitrns010:ver2.0:extended:urn:www.peppol.eu:bis:peppol5a:ver2.0:extended:urn:www.difi.no:ehf:faktura:ver2.0");
        assertNotNull(declarationIdentifier.getParent());
    }
}
