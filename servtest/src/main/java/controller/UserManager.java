package controller;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.regexp.internal.RESyntaxException;
import dao.UserDao;
import dao.UserDaoBuilder;
import dao.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * The class is responsible for data filtering and json file formation.
 */
@RestController
public class UserManager {

    private UserDao userDao;

    public UserManager() {
        userDao = new UserDaoBuilder().createUserDao();
    }

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/hello/contacts")
    public String findContacts(@RequestParam(value="nameFilter", defaultValue="") String regex)
    {
        boolean checkRegex = true;
        try {
            RE re = new RE(regex);
        } catch (RESyntaxException e) {
            checkRegex = false;
        }

        if (regex.toLowerCase().equals("all"))
        {
        return findUsers("");
        }
        else if(regex.isEmpty() | !checkRegex)
        {
            return ExcpManager.getBadRequest().toString();
        }
        else {
            String result = findUsers(regex);
            result = result.isEmpty() ? ExcpManager.getNotFound().toString() : result;

            return result;
        }
    }

    public String findUsers(String regex) {
        Map<String, User> users = userDao.getUsers();

        Set<Map.Entry<String, User>> entrySet = users.entrySet()
                .stream().filter(s -> !s.getKey()
                        .matches(regex))
                .collect(Collectors.toSet());

        String result = "";
        if (!entrySet.isEmpty()) {
            JSONObject json = new JSONObject();
            JSONArray arr = new JSONArray();
            JSONObject node;
            for (Map.Entry<String, User> entry : entrySet) {
                node = new JSONObject();
                node.put("id", entry.getValue().getId());
                node.put("name", entry.getValue().getName());
                arr.add(node);
            }

            json.put("contacts", arr);
            System.out.println(json.toString());

            result = json.toJSONString();
        }
        return result;
    }
}
