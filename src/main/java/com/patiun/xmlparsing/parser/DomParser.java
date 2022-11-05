package com.patiun.xmlparsing.parser;

import com.patiun.xmlparsing.entity.Flower;
import com.patiun.xmlparsing.entity.Plant;
import com.patiun.xmlparsing.entity.Soil;
import com.patiun.xmlparsing.entity.Tree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(DomParser.class);

    private static final String PLANTS_TAG = "plants";
    private static final String FLOWER_TAG = "flower";
    private static final String TREE_TAG = "tree";
    private static final String ID_TAG = "id";
    private static final String ORIGIN_TAG = "origin";
    private static final String NAME_TAG = "name";
    private static final String SOIL_TAG = "soilType";
    private static final String TIPS_TAG = "growingTips";
    private static final String NEEDED_TEMPERATURE_TAG = "neededTemperature";
    private static final String NEEDS_LIGHT_TAG = "needsLight";
    private static final String WEEKLY_REQUIRED_WATER_TAG = "weeklyRequiredWater";
    private static final String PETAL_NUMBER_TAG = "petalNumber";
    private static final String TRUNK_DIAMETER_TAG = "trunkDiameter";

    private void buildPlantFields(Element plantElement, Plant.PlantBuilder builder) {
        String idLine = plantElement.getAttribute(ID_TAG);
        int id = Integer.parseInt(idLine);
        builder.setId(id);
        String origin = plantElement.getAttribute(ORIGIN_TAG);
        builder.setOrigin(origin);
        String name = getElementTextContent(plantElement, NAME_TAG);
        builder.setName(name);
        String soilTypeLine = getElementTextContent(plantElement, SOIL_TAG);
        Soil soilType = Soil.valueOf(soilTypeLine);
        builder.setSoilType(soilType);
        NodeList tipsNodes = plantElement.getElementsByTagName(TIPS_TAG);
        Element tipsNode = (Element) tipsNodes.item(0);
        String neededTemperatureLine = getElementTextContent(tipsNode, NEEDED_TEMPERATURE_TAG);
        int neededTemperature = Integer.parseInt(neededTemperatureLine);
        builder.setNeededTemperature(neededTemperature);
        String needsLightLine = getElementTextContent(tipsNode, NEEDS_LIGHT_TAG);
        boolean needsLight = Boolean.parseBoolean(needsLightLine);
        builder.setNeedsLight(needsLight);
        String weeklyRequiredWaterLine = getElementTextContent(tipsNode, WEEKLY_REQUIRED_WATER_TAG);
        int weeklyRequiredWater = Integer.parseInt(weeklyRequiredWaterLine);
        builder.setWeeklyRequiredWater(weeklyRequiredWater);
    }

    private Flower buildFlower(Element flowerElement) {
        LOGGER.info("Started building a flower from found " + FLOWER_TAG + " element");
        Flower.FlowerBuilder flowerBuilder = new Flower.FlowerBuilder();
        buildPlantFields(flowerElement, flowerBuilder);
        String petalNumberLine = getElementTextContent(flowerElement, PETAL_NUMBER_TAG);
        int petalNumber = Integer.parseInt(petalNumberLine);
        flowerBuilder.setPetalNumber(petalNumber);
        return new Flower(flowerBuilder);
    }

    private Tree buildTree(Element treeElement) {
        LOGGER.info("Started building a tree from found " + TREE_TAG + " element");
        Tree.TreeBuilder treeBuilder = new Tree.TreeBuilder();
        buildPlantFields(treeElement, treeBuilder);
        String trunkDiameterLine = getElementTextContent(treeElement, TRUNK_DIAMETER_TAG);
        int trunkDiameter = Integer.parseInt(trunkDiameterLine);
        treeBuilder.setTrunkDiameter(trunkDiameter);
        return new Tree(treeBuilder);
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    @Override
    public List<Plant> parse(String filePath) throws ParserException {
        LOGGER.info("Started parsing plants from " + filePath);
        try {
            List<Plant> plantList = new ArrayList<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();
            NodeList plantsNodeList = root.getChildNodes();
            Element currentElement;
            for (int i = 0; i < plantsNodeList.getLength(); i++) {
                if (plantsNodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                currentElement = (Element) plantsNodeList.item(i);
                if (currentElement.getTagName().equals(FLOWER_TAG)) {
                    plantList.add(buildFlower((Element) plantsNodeList.item(i)));
                } else if (currentElement.getTagName().equals(TREE_TAG)) {
                    plantList.add(buildTree((Element) plantsNodeList.item(i)));
                }
            }
            return plantList;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException(e);
        }
    }
}
