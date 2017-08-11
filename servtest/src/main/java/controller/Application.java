package controller;

import dao.UserDaoBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new UserManager(
                new UserDaoBuilder()
                .setPassword("root")
                .setUser("root")
                .setPort(3307)
                .createUserDao()
        );
        SpringApplication.run(Application.class, args);
    }
}
