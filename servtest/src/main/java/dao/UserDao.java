package dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The class is responsible for database cashing. In the current implementation we loose
 * disk space, but at the same time having advantage in access speed. If disk space is limited,
 * cashing can be carried out partially.
 */
public class UserDao {

    private static final String HOST_URL = "jdbc:mysql://localhost:";
    private static Integer port;
    private static String dbUrl;
    private static final String DB_NAME = "restservdbtest";
    private static final String TABLE_NAME = "contacts";
    private static String user;
    private static String password;

    private Map<String, User> users;

    UserDao(Integer port, String user, String password) {

        if (port == null) {
            this.port = 3307;
        } else this.port = port;

        if (user == null) {
            this.user = "root";
        } else this.user = user;

        if (password == null) {
            this.password = "root";
        } else this.password = password;

        dbUrl = HOST_URL + this.port;
        users = new HashMap<>();
        new DataBaseTrial(DB_NAME, TABLE_NAME);
        init();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    private void init() {

        try {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                String query = "SELECT * FROM " + DB_NAME + "." + TABLE_NAME + " WHERE id > 0;";
                con = getConnection();
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();

                if (rs != null) {
                    User user;
                    String name;

                    while (rs.next()) {
                        name = rs.getString("name");
                        user = new User(rs.getInt("id"), name);
                        users.put(name, user);
                    }
                }
            } finally {
                if (con != null) {
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, user, password);
    }
}
