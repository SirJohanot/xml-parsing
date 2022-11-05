package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Flower;
import com.epam.xmlparsing.entity.Plant;
import com.epam.xmlparsing.entity.Soil;
import com.epam.xmlparsing.entity.Tree;
import com.epam.xmlparsing.validation.ValidatorException;
import com.epam.xmlparsing.validation.XMLValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class DirectorTest {

    private static final String XML_FILE_PATH = "xmlFile.xml";
    private static final String XSD_FILE_PATH = "xsdFile.xsd";
    private static final List<Plant> LIST_WITH_TWO_FLOWERS_AND_TWO_TREES = Arrays.asList(
            new Flower(1, "", "Rose", Soil.CLAY, 20, true, 1500, 40),
            new Flower(2, "Persia", "Dandelion", Soil.SILT, 15, true, 750, 50),
            new Tree(3, "", "Oak", Soil.CLAY, 5, false, 560, 30.0),
            new Tree(4, "North America", "Paper Birch", Soil.SAND, 5, true, 430, 40.0));

    @Test
    public void testParseShouldReturnAListOfPlantsWhenXmlIsValid() throws ParserException, DirectorException, ValidatorException {
        //given
        XMLValidator validator = Mockito.mock(XMLValidator.class);
        Mockito.when(validator.validate(XML_FILE_PATH, XSD_FILE_PATH)).thenReturn(true);
        Parser parser = Mockito.mock(Parser.class);
        Mockito.when(parser.parse(XML_FILE_PATH)).thenReturn(LIST_WITH_TWO_FLOWERS_AND_TWO_TREES);
        Director director = new Director(parser, validator);
        //when
        List<Plant> actualList = director.parse(XML_FILE_PATH, XSD_FILE_PATH);
        //then
        Assert.assertEquals(LIST_WITH_TWO_FLOWERS_AND_TWO_TREES, actualList);
    }

    @Test(expected = DirectorException.class)
    public void testParseShouldThrowDirectorExceptionWhenXmlFileIsNotValid() throws ParserException, DirectorException, ValidatorException {
        //given
        XMLValidator validator = Mockito.mock(XMLValidator.class);
        Mockito.when(validator.validate(XML_FILE_PATH, XSD_FILE_PATH)).thenReturn(false);
        Parser parser = Mockito.mock(Parser.class);
        Mockito.when(parser.parse(XML_FILE_PATH)).thenReturn(LIST_WITH_TWO_FLOWERS_AND_TWO_TREES);
        Director director = new Director(parser, validator);
        //when
        List<Plant> actualList = director.parse(XML_FILE_PATH, XSD_FILE_PATH);
        //then
        Assert.assertEquals(LIST_WITH_TWO_FLOWERS_AND_TWO_TREES, actualList);
    }
}
