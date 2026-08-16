package network.oxalis.vefa.validator.declaration;

import network.oxalis.vefa.validator.api.Declaration;
import network.oxalis.vefa.validator.api.Expectation;
import network.oxalis.vefa.validator.expectation.XmlExpectation;
import network.oxalis.vefa.validator.lang.ValidatorException;

import javax.xml.stream.XMLInputFactory;

abstract class AbstractXmlDeclaration implements Declaration {

    protected static final XMLInputFactory XML_INPUT_FACTORY = XMLInputFactory.newFactory();

    @Override
    public Expectation expectations(byte[] content) throws ValidatorException {
        return new XmlExpectation(content);
    }
}
