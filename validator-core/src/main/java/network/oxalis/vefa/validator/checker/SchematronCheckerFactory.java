package network.oxalis.vefa.validator.checker;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import net.sf.saxon.s9api.*;
import network.oxalis.vefa.validator.annotation.Type;
import network.oxalis.vefa.validator.api.ArtifactHolder;
import network.oxalis.vefa.validator.api.Checker;
import network.oxalis.vefa.validator.api.CheckerFactory;
import network.oxalis.vefa.validator.lang.ValidatorException;
import network.oxalis.vefa.validator.util.SaxonErrorListener;
import network.oxalis.vefa.validator.util.SaxonMessageListener;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * Implementation performing step 3 (compilation) of Schematron.
 *
 * @author erlend
 */
@Type(".sch")
public class SchematronCheckerFactory implements CheckerFactory {

    @Inject
    @Named("schematron-step3")
    private Provider<XsltExecutable> schematronCompiler;

    @Inject
    private Processor processor;

    @Inject
    private Injector injector;

    @Override
    public Checker prepare(ArtifactHolder artifactHolder, String path) throws ValidatorException {
        try (InputStream inputStream = artifactHolder.getInputStream(path)) {
            XdmDestination destination = new XdmDestination();

            XsltTransformer xsltTransformer = schematronCompiler.get().load();
            xsltTransformer.setErrorListener(SaxonErrorListener.INSTANCE);
            xsltTransformer.setMessageListener(SaxonMessageListener.INSTANCE);
            xsltTransformer.setSource(new StreamSource(inputStream));
            xsltTransformer.setDestination(destination);
            xsltTransformer.transform();

            XsltCompiler xsltCompiler = processor.newXsltCompiler();
            xsltCompiler.setErrorListener(SaxonErrorListener.INSTANCE);

            Checker checker = new SchematronXsltChecker(processor, xsltCompiler.compile(destination.getXdmNode().asSource()));
            injector.injectMembers(checker);
            return checker;
        } catch (Exception e) {
            throw new ValidatorException(e.getMessage(), e);
        }
    }
}
