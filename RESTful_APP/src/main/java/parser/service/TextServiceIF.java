package parser.service;
import parser.model.ParsedValues;

import java.sql.SQLException;
import java.util.List;

public interface TextServiceIF {

    List<ParsedValues> getAllFiles() throws SQLException;
    void addValue(ParsedValues parsedValues) throws SQLException;
    void editParsedFile(ParsedValues parsedValues) throws SQLException;
    ParsedValues checkValue(String value) throws SQLException;

}
