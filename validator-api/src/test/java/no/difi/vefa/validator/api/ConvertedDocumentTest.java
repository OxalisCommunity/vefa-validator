package no.difi.vefa.validator.api;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;

public class ConvertedDocumentTest {

    @Test
    public void simple() {
        ConvertedDocument document = new ConvertedDocument(new ByteArrayInputStream(new byte[] {}),
                new ByteArrayInputStream(new byte[] {}), "identifier", null);

        assertNotNull(document.getInputStream());
        assertNotNull(document.getSource());
        assertEquals(document.getDeclarations().get(0), "identifier");
        assertNull(document.getExpectation());
    }

}
