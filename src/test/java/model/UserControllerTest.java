package model;

import http.HttpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testhelper.Common;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest {
    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    private static UserController userController;

    static {
        userController = new UserController();
        try {
            HttpRequest httpRequest = Common.parsePostHttpRequest("HTTP_POST_USER_CREATE.txt");
            userController.addUser(httpRequest);
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    @Test
    @DisplayName("로그인 성공했을 때 index.html 반환")
    public void loginSuccess() throws IOException {
        HttpRequest httpRequest = Common.parsePostHttpRequest("HTTP_POST_USER_LOGIN.txt");
        String url = userController.login(httpRequest);
        assertThat(url).isEqualTo("/index.html");
    }

    @Test
    @DisplayName("로그인 실패했을 때 /user/login_failed.html 반환")
    public void loginFail() throws IOException {
        HttpRequest httpRequest = Common.parsePostHttpRequest("HTTP_POST_USER_FAILED_LOGIN.txt");
        String url = userController.login(httpRequest);
        assertThat(url).isEqualTo("/user/login_failed.html");
    }
}