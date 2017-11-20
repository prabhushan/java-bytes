package com.javabytes.edifact.version;

public interface ParserFactory {
 EdifactParser getParser(String type);
}
