package model;

import db.DataBase;
import http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public String addUser(HttpRequest httpRequest) {
        User user = makeUser(httpRequest);

        DataBase.addUser(user);
        logger.debug("insert user : {}", user);
        logger.debug("user list : {}", DataBase.findAll());

        return "/index.html";
    }

    public String login(final HttpRequest httpRequest) {
        User loginUser = makeUser(httpRequest);
        try {
            User currentUser = Optional.ofNullable(DataBase.findUserById(loginUser.getUserId()))
                    .orElseThrow(IllegalArgumentException::new);
            if (loginUser.authenticate(currentUser)) {
                return "redirect:/index.html";
            }
            return "/user/login_failed.html";
        } catch (IllegalArgumentException e) {
            return "/user/login_failed.html";
        }
    }

    private User makeUser(HttpRequest httpRequest) {
        return new User(
                httpRequest.getParameter("userId"),
                httpRequest.getParameter("password"),
                httpRequest.getParameter("name"),
                httpRequest.getParameter("email"));
    }
}
