package network.oxalis.vefa.validator.checker;

import network.oxalis.vefa.validator.annotation.Type;
import network.oxalis.vefa.validator.api.ArtifactHolder;
import network.oxalis.vefa.validator.api.Checker;
import network.oxalis.vefa.validator.api.CheckerFactory;
import network.oxalis.vefa.validator.lang.ValidatorException;
import network.oxalis.vefa.validator.util.HolderLSResolveResource;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;

/**
 * @author erlend
 */
@Type(".xsd")
public class XsdCheckerFactory implements CheckerFactory {

    @Override
    public Checker prepare(ArtifactHolder artifactHolder, String path) throws ValidatorException {
        try (InputStream inputStream = artifactHolder.getInputStream(path)) {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schemaFactory.setResourceResolver(new HolderLSResolveResource(artifactHolder, path));
            return new XsdChecker(schemaFactory.newSchema(new StreamSource(inputStream)));
        } catch (Exception e) {
            throw new ValidatorException(e.getMessage(), e);
        }
    }
}
