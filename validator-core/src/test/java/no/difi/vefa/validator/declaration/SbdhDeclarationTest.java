package no.difi.vefa.validator.declaration;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.inject.Guice;
import com.google.inject.Inject;
import no.difi.vefa.validator.api.CachedFile;
import no.difi.vefa.validator.module.ValidatorModule;
import no.difi.vefa.validator.util.DeclarationDetector;
import no.difi.vefa.validator.util.DeclarationIdentifier;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;

public class SbdhDeclarationTest {

    @Inject
    private DeclarationDetector declarationDetector;

    @Before
    public void beforeClass() {
        Guice.createInjector(new ValidatorModule()).injectMembers(this);
    }

    @Test
    public void simpleSbdh() throws Exception {

        try (InputStream inputStream = new BufferedInputStream(getClass().getResourceAsStream("/documents/peppol-bis-invoice-sbdh.xml"))) {
            DeclarationIdentifier declarationIdentifier = declarationDetector.detect(inputStream);
            assertEquals(declarationIdentifier.getIdentifier().get(1), "SBDH:1.0");
            Iterator<CachedFile> iterator = declarationIdentifier.getDeclaration().children(inputStream).iterator();
            assertTrue(iterator.hasNext());
        }
    }

    @Test
    public void simpleSbdhOnly() throws Exception {

        try (InputStream inputStream = new BufferedInputStream(getClass().getResourceAsStream("/documents/sbdh-only.xml"))) {
            DeclarationIdentifier declarationIdentifier = declarationDetector.detect(inputStream);
            assertEquals(declarationIdentifier.getIdentifier().get(1), "SBDH:1.0");
            Iterator<CachedFile> iterator = declarationIdentifier.getDeclaration().children(inputStream).iterator();
            assertFalse(iterator.hasNext());
        }
    }
}
