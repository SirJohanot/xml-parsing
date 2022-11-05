package com.patiun.xmlparsing.parser;

import com.patiun.xmlparsing.entity.Plant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(SaxParser.class);

    @Override
    public List<Plant> parse(String filePath) throws ParserException {
        LOGGER.info("Started parsing plants from " + filePath);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        PlantHandler handler = new PlantHandler();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(filePath, handler);
            return handler.getPlants();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException(e);
        }
    }
}
