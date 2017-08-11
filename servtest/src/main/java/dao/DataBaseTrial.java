package dao;

import java.sql.*;

/**
 * The class creates a test database.
 */
class DataBaseTrial {
    private static String CREATE_DB_QUERY;
    private static String CREATE_TABLE_QUERY;
    private String dbName;
    private String tableName;

    DataBaseTrial(String dbName, String tableName){
        CREATE_DB_QUERY = "CREATE DATABASE IF NOT EXISTS `" + dbName + "`;";
        CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `" + dbName + "`.`"
                + tableName + "` (\n" + "  `id` INT NOT NULL,\n"
                + "  `name` VARCHAR(45) NULL,\n"
                + "  PRIMARY KEY (`id`));";

        this.dbName = dbName;
        this.tableName = tableName;
        checkDB();
    }

    private void checkDB()
    {
        try
        {
            try (Connection conn = UserDao.getConnection())
            {
                try (PreparedStatement ps = conn.prepareStatement(CREATE_DB_QUERY))
                {
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = conn.prepareStatement(CREATE_TABLE_QUERY))
                {
                    ps.executeUpdate();
                }
                addTestUsers();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void addTestUsers()
    {
        try
        {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try
            {
                conn = UserDao.getConnection();
                String query = "SELECT COUNT(*) as cnt FROM " + dbName + "." + tableName;
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                rs.next();
                int cnt = rs.getInt("cnt");

                if (cnt == 0)
                {
                    int count = 10;
                    String[] names = new String[]{
                            "Tom",
                            "Tim",
                            "Adam",
                            "Tem",
                            "John",
                            "Jack",
                            "Dick",
                            "Norman",
                            "Adolf",
                            "Valera"
                    };
                    String values = "VALUES";
                    for (int i = 0; i < count; i++)
                    {
                        values += "(?,?),";
                    }
                    values = values.substring(0, values.length() - 1);

                    query = "INSERT INTO " + dbName + "." + tableName + " " + values + ";";
                    ps = conn.prepareStatement(query);
                    int k = 0, m = 0;
                    for (int i = 0; i < count; i++)
                    {
                        ps.setInt(++k, ++m);
                        ps.setString(++k, names[i]);
                    }

                    ps.executeUpdate();
                }
            }
            finally
            {
                if (conn != null)
                {
                    conn.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (rs != null)
                {
                    rs.close();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
