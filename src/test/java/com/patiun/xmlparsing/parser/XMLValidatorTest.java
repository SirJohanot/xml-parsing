package com.patiun.xmlparsing.parser;

import com.patiun.xmlparsing.validation.ValidatorException;
import com.patiun.xmlparsing.validation.XMLValidator;
import org.junit.Assert;
import org.junit.Test;

public class XMLValidatorTest {

    private static final XMLValidator VALIDATOR = new XMLValidator();
    private static final String FIRST_VALID_XML_FILE = "src/test/resources/plants.xml";
    private static final String FIRST_INVALID_XML_FILE = "src/test/resources/invalidPlants.xml";
    private static final String FIRST_XSD_FILE = "src/test/resources/plants.xsd";

    @Test
    public void testValidateShouldReturnTrueForAValidXmlXsdPair() throws ValidatorException {
        //given
        //when
        boolean actualIsValid = VALIDATOR.validate(FIRST_VALID_XML_FILE, FIRST_XSD_FILE);
        //then
        Assert.assertTrue(actualIsValid);
    }

    @Test
    public void testValidateShouldReturnFalseForAnInvalidXmlXsdPair() throws ValidatorException {
        //given
        //when
        boolean actualIsValid = VALIDATOR.validate(FIRST_INVALID_XML_FILE, FIRST_XSD_FILE);
        //then
        Assert.assertFalse(actualIsValid);
    }
}
