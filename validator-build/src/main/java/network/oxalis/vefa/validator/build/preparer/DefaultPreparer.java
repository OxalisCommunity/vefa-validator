package network.oxalis.vefa.validator.build.preparer;

import com.google.common.io.Files;
import network.oxalis.vefa.validator.annotation.Type;
import network.oxalis.vefa.validator.api.Preparer;
import network.oxalis.vefa.validator.build.util.PreparerProvider;

import java.io.IOException;
import java.nio.file.Path;

@Type(PreparerProvider.DEFAULT)
public class DefaultPreparer implements Preparer {

    @Override
    public void prepare(Path source, Path target, Type type) throws IOException {
        Files.copy(source.toFile(), target.toFile());
    }
}
