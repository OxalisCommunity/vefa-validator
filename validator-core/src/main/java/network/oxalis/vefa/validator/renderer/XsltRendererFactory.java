package network.oxalis.vefa.validator.renderer;

import com.google.inject.Inject;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XsltCompiler;
import network.oxalis.vefa.validator.annotation.Type;
import network.oxalis.vefa.validator.api.ArtifactHolder;
import network.oxalis.vefa.validator.api.Renderer;
import network.oxalis.vefa.validator.api.RendererFactory;
import network.oxalis.vefa.validator.lang.ValidatorException;
import network.oxalis.vefa.validator.util.HolderURIResolver;
import network.oxalis.vefa.validator.util.SaxonErrorListener;
import no.difi.xsd.vefa.validator._1.StylesheetType;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * @author erlend
 */
@Deprecated
@Type({".xsl", ".xslt"})
public class XsltRendererFactory implements RendererFactory {

    @Inject
    private Processor processor;

    @Override
    public Renderer prepare(StylesheetType stylesheetType, ArtifactHolder artifactHolder, String path) throws ValidatorException {
        try (InputStream inputStream = artifactHolder.getInputStream(path)) {
            XsltCompiler xsltCompiler = processor.newXsltCompiler();
            xsltCompiler.setErrorListener(SaxonErrorListener.INSTANCE);
            xsltCompiler.setURIResolver(new HolderURIResolver(artifactHolder, path));
            return new XsltRenderer(xsltCompiler.compile(new StreamSource(inputStream)),
                    stylesheetType, artifactHolder, path, processor);
        } catch (Exception e) {
            throw new ValidatorException(e.getMessage(), e);
        }
    }
}
