package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Plant;
import com.epam.xmlparsing.entity.PlantsWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class JaxbParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(JaxbParser.class);

    @Override
    public List<Plant> parse(String filePath) throws ParserException {
        LOGGER.info("Started parsing plants from " + filePath);
        try {
            JAXBContext jc = JAXBContext.newInstance(PlantsWrapper.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(filePath);
            PlantsWrapper plantsWrapper = (PlantsWrapper) u.unmarshal(reader);
            return plantsWrapper.getPlants();
        } catch (JAXBException | FileNotFoundException e) {
            throw new ParserException(e);
        }
    }
}
