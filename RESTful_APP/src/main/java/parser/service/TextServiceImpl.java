package parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parser.dao.ParserDaoIF;
import parser.model.ParsedValues;

import java.sql.SQLException;
import java.util.List;


@Service
public class TextServiceImpl implements TextServiceIF {

    @Autowired
    private ParserDaoIF parserDaoIF;

    @Override
    @Transactional
    public List<ParsedValues> getAllFiles() throws SQLException {
        return parserDaoIF.getAllFiles();
    }

    @Override
    @Transactional
    public void addValue(ParsedValues parsedValue) throws SQLException {
        parserDaoIF.addValue(parsedValue);
    }

    @Override
    @Transactional
    public void editParsedFile(ParsedValues parsedValues) throws SQLException {
        parserDaoIF.editParsedFile(parsedValues);
    }

    @Override
    @Transactional
    public ParsedValues checkValue(String value) throws SQLException {
        return parserDaoIF.checkValue(value);
    }

}
