package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Plant;

import java.util.List;

public interface Parser {

    List<Plant> parse(String filePath) throws ParserException;
}
