package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Plant;
import com.epam.xmlparsing.validation.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Director {

    private static final Logger LOGGER = LogManager.getLogger(Director.class);

    private final Parser parser;
    private final XMLValidator validator;

    public Director(Parser parser, XMLValidator xmlValidator) {
        this.parser = parser;
        this.validator = xmlValidator;
    }

    List<Plant> parse(String xmlPath, String xsdPath) throws DirectorException {
        LOGGER.info("Started parsing plants from " + xmlPath + " while validating by " + xsdPath);
        try {
            if (!validator.validate(xmlPath, xsdPath)) {
                throw new DirectorException("The xml file by specified path does not validate by the xsd file by inputted path");
            }
            return parser.parse(xmlPath);
        } catch (Exception e) {
            throw new DirectorException(e);
        }
    }
}
