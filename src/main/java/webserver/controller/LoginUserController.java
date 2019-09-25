package webserver.controller;

import http.HttpRequest;
import http.HttpResponse;
import model.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.support.PathHandler;

import java.io.IOException;
import java.net.URISyntaxException;

// @TODO 싱글톤 고려
public class LoginUserController extends HttpController {
    private static final Logger log = LoggerFactory.getLogger(LoginUserController.class);

    @Override
    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException, URISyntaxException {
        String url = new UserController().login(httpRequest);
        if (url.contains("redirect:")) {
            String location = url.substring(url.indexOf("redirect:") + 1);
            log.debug("Redirect Location in LoginUserController : {}", location);

            httpResponse.addHeader("Content-Type", "text/html");
            httpResponse.addHeader("Location", location);
            httpResponse.sendRedirect();
        }
        String absoluteUrl = PathHandler.path(url);
        httpResponse.forward(absoluteUrl);
    }
}
