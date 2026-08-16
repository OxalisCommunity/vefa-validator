package network.oxalis.vefa.validator.checker;

import com.google.inject.Inject;
import com.google.inject.Injector;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XsltCompiler;
import network.oxalis.vefa.validator.annotation.Type;
import network.oxalis.vefa.validator.api.ArtifactHolder;
import network.oxalis.vefa.validator.api.Checker;
import network.oxalis.vefa.validator.api.CheckerFactory;
import network.oxalis.vefa.validator.lang.ValidatorException;
import network.oxalis.vefa.validator.util.SaxonErrorListener;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * @author erlend
 */
@Type({".xsl", ".xslt", ".svrl.xsl", ".svrl.xslt", ".sch.xslt"})
public class SchematronXsltCheckerFactory implements CheckerFactory {

    @Inject
    private Processor processor;

    @Inject
    private Injector injector;

    @Override
    public Checker prepare(ArtifactHolder artifactHolder, String path) throws ValidatorException {
        try (InputStream inputStream = artifactHolder.getInputStream(path)) {
            XsltCompiler xsltCompiler = processor.newXsltCompiler();
            xsltCompiler.setErrorListener(SaxonErrorListener.INSTANCE);

            Checker checker = new SchematronXsltChecker(processor, xsltCompiler.compile(new StreamSource(inputStream)));
            injector.injectMembers(checker);
            return checker;

        } catch (Exception e) {
            throw new ValidatorException(e.getMessage(), e);
        }
    }
}
