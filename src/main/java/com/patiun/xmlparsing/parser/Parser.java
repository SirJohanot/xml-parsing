package com.patiun.xmlparsing.parser;

import com.patiun.xmlparsing.entity.Plant;

import java.util.List;

public interface Parser {

    List<Plant> parse(String filePath) throws ParserException;
}
