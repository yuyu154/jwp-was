package webserver.controller;

import http.HttpRequest;
import http.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import static testhelper.Common.parsePostHttpRequest;

public class LoginUserControllerTest {
    private static final Logger log = LoggerFactory.getLogger(LoginUserControllerTest.class);

    @Test
    @DisplayName("로그인 성공했을 때 /index.html으로 fowarding한다")
    public void loginSuccess() throws IOException, URISyntaxException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpResponse httpResponse = new HttpResponse(byteArrayOutputStream);
        HttpRequest httpRequest = parsePostHttpRequest("HTTP_POST_USER_LOGIN.txt");

        LoginUserController loginUserController = new LoginUserController();
        loginUserController.doPost(httpRequest, httpResponse);
        log.info(byteArrayOutputStream.toString());
    }
}