package network.oxalis.vefa.validator.declaration;

import com.google.inject.Guice;
import com.google.inject.Inject;
import network.oxalis.vefa.validator.api.CachedFile;
import network.oxalis.vefa.validator.module.ValidatorModule;
import network.oxalis.vefa.validator.util.DeclarationDetector;
import network.oxalis.vefa.validator.util.DeclarationIdentifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;

import static org.testng.Assert.*;

public class SbdhDeclarationTest {

    @Inject
    private DeclarationDetector declarationDetector;

    @BeforeClass
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
