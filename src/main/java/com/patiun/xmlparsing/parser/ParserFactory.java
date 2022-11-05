package com.patiun.xmlparsing.parser;

public class ParserFactory {

    private ParserFactory() {
    }

    static Parser getParser(ParserType parserType) throws ParserFactoryException {
        switch (parserType) {
            case JAXB:
                return new JaxbParser();
            case DOM:
                return new DomParser();
            case SAX:
                return new SaxParser();
            default:
                throw new ParserFactoryException("Could not identify the parser type");
        }
    }
}
