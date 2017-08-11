package parser.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import parser.model.ParsedValues;

import java.sql.SQLException;
import java.util.List;

@Repository
public class ParserDaoImpl implements ParserDaoIF {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Retreives data from the database
     * @return parsed values from the database
     * @throws SQLException
     */
    @Override
    public List<ParsedValues> getAllFiles() throws SQLException {
        List<ParsedValues> parsedValues = null;
        Session session = null;
        final String TABLE_NAME = "parsed_file";
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT count, value FROM " + TABLE_NAME;
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            parsedValues = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return parsedValues;
    }

    /**
     * Adds values to the database
     * @throws SQLException
     */
    @Override
    @Transactional
    public void addValue(ParsedValues parsedValues) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(parsedValues);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null)) {
                session.close();
            }
        }
    }

    /**
     * Updates value count if a word from the file already exists in the database
     * @throws SQLException
     */
    @Override
    @Transactional
    public void editParsedFile(ParsedValues parsedValues) throws SQLException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(parsedValues);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null)) {
                session.close();
            }
        }
    }

    /**
     * Checks whether a word from the file already exists in the database
     * @throws SQLException
     */
    @Override
    public ParsedValues checkValue(String value) throws SQLException {
        Session session = null;
        ParsedValues parsedValues = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ParsedValues.class);
            parsedValues = (ParsedValues) criteria.add(Restrictions.like("value", value)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return parsedValues;
    }

}
