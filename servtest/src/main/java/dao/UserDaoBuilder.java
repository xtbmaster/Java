package dao;

public class UserDaoBuilder {

    private Integer port;
    private String user;
    private String password;

    public UserDaoBuilder setPort(Integer port) {
        this.port = port;
        return this;
    }

    public UserDaoBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    public UserDaoBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDao createUserDao() {
        return new UserDao(port, user, password);
    }
}