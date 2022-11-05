package com.patiun.xmlparsing.parser;

import com.patiun.xmlparsing.entity.Flower;
import com.patiun.xmlparsing.entity.Plant;
import com.patiun.xmlparsing.entity.Soil;
import com.patiun.xmlparsing.entity.Tree;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JaxbParserTest {

    private static final JaxbParser PARSER = new JaxbParser();
    private static final String FILE_WITH_TWO_FLOWERS_AND_TWO_TREES = "src/test/resources/plants.xml";
    private static final List<Plant> LIST_WITH_TWO_FLOWERS_AND_TWO_TREES = Arrays.asList(
            new Flower(1, "", "Rose", Soil.CLAY, 20, true, 1500, 40),
            new Flower(2, "Persia", "Dandelion", Soil.SILT, 15, true, 750, 50),
            new Tree(3, "", "Oak", Soil.CLAY, 5, false, 560, 30.0),
            new Tree(4, "North America", "Paper Birch", Soil.SAND, 5, true, 430, 40.0));

    @Test
    public void testParseShouldReturnListOfPlantsWhenThereAreBothTreesAndFlowersInFile() throws ParserException {
        //given
        //when
        List<Plant> actualList = PARSER.parse(FILE_WITH_TWO_FLOWERS_AND_TWO_TREES);
        //then
        Assert.assertEquals(LIST_WITH_TWO_FLOWERS_AND_TWO_TREES, actualList);
    }

}
