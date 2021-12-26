package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Plant;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser {
    @Override
    public List<Plant> parse(String filePath) throws ParserException {
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