package com.patiun.xmlparsing.parser;

import com.patiun.xmlparsing.entity.Flower;
import com.patiun.xmlparsing.entity.Plant;
import com.patiun.xmlparsing.entity.Soil;
import com.patiun.xmlparsing.entity.Tree;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PlantHandler extends DefaultHandler {

    private static final String PLANTS = "plants";
    private static final String TREE = "tree";
    private static final String FLOWER = "flower";
    private static final String ID = "id";
    private static final String ORIGIN = "origin";
    private static final String NAME = "name";
    private static final String SOIL_TYPE = "soilType";
    private static final String GROWING_TIPS = "growingTips";
    private static final String NEEDED_TEMPERATURE = "neededTemperature";
    private static final String NEEDS_LIGHT = "needsLight";
    private static final String WEEKLY_REQUIRED_WATER = "weeklyRequiredWater";
    private static final String PETAL_NUMBER = "petalNumber";
    private static final String TRUNK_DIAMETER = "trunkDiameter";

    private List<Plant> plants;
    private StringBuilder bufferedString;
    private Tree.TreeBuilder bufferedTreeBuilder;
    private Flower.FlowerBuilder bufferedFlowerBuilder;
    private Plant.PlantBuilder currentPlantBuilder;

    public PlantHandler() {
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String elementName, Attributes attributes) throws SAXException {
        switch (elementName) {
            case PLANTS:
                plants = new ArrayList<>();
                break;
            case TREE:
                bufferedTreeBuilder = new Tree.TreeBuilder();
                parseAttributes(bufferedTreeBuilder, attributes);
                currentPlantBuilder = bufferedTreeBuilder;
                break;
            case FLOWER:
                bufferedFlowerBuilder = new Flower.FlowerBuilder();
                parseAttributes(bufferedFlowerBuilder, attributes);
                currentPlantBuilder = bufferedFlowerBuilder;
                break;
            default:
                if (elementName.equals(NAME) || elementName.equals(SOIL_TYPE) || elementName.equals(NEEDED_TEMPERATURE) || elementName.equals(NEEDS_LIGHT) || elementName.equals(WEEKLY_REQUIRED_WATER) || elementName.equals(PETAL_NUMBER) || elementName.equals(TRUNK_DIAMETER)) {
                    bufferedString = new StringBuilder();
                }
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String elementName) throws SAXException {
        switch (elementName) {
            case TREE:
                plants.add(new Tree(bufferedTreeBuilder));
                break;
            case FLOWER:
                plants.add(new Flower(bufferedFlowerBuilder));
                break;
            case NAME:
                String name = bufferedString.toString();
                currentPlantBuilder.setName(name);
                break;
            case SOIL_TYPE:
                String soilTypeLine = bufferedString.toString();
                Soil soilType = Soil.valueOf(soilTypeLine);
                currentPlantBuilder.setSoilType(soilType);
                break;
            case NEEDED_TEMPERATURE:
                String neededTemperatureLine = bufferedString.toString();
                int neededTemperature = Integer.parseInt(neededTemperatureLine);
                currentPlantBuilder.setNeededTemperature(neededTemperature);
                break;
            case NEEDS_LIGHT:
                String needsLightLine = bufferedString.toString();
                boolean needsLight = Boolean.parseBoolean(needsLightLine);
                currentPlantBuilder.setNeedsLight(needsLight);
                break;
            case WEEKLY_REQUIRED_WATER:
                String weeklyRequiredWaterLine = bufferedString.toString();
                int weeklyRequiredWater = Integer.parseInt(weeklyRequiredWaterLine);
                currentPlantBuilder.setWeeklyRequiredWater(weeklyRequiredWater);
                break;
            case PETAL_NUMBER:
                String petalNumberLine = bufferedString.toString();
                int petalNumber = Integer.parseInt(petalNumberLine);
                bufferedFlowerBuilder.setPetalNumber(petalNumber);
                break;
            case TRUNK_DIAMETER:
                String trunkDiameterLine = bufferedString.toString();
                double trunkDiameter = Double.parseDouble(trunkDiameterLine);
                bufferedTreeBuilder.setTrunkDiameter(trunkDiameter);
                break;
        }
    }

    @Override
    public void characters(char[] characters, int start, int length) throws SAXException {
        if (bufferedString == null) {
            bufferedString = new StringBuilder();
        } else {
            bufferedString.append(characters, start, length);
        }
    }

    private void parseAttributes(Plant.PlantBuilder destination, Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getLocalName(i).equals(ID)) {
                String idLine = attributes.getValue(i);
                int id = Integer.parseInt(idLine);
                destination.setId(id);
            } else if (attributes.getLocalName(i).equals(ORIGIN)) {
                String origin = attributes.getValue(i);
                destination.setOrigin(origin);
            }
        }
    }

    List<Plant> getPlants() {
        return plants;
    }
}
