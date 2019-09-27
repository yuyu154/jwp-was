package servlet.controller;

import http.request.HttpRequest;
import http.response.HttpResponse;
import domain.UserService;
import servlet.resolver.UserResolver;

import java.io.IOException;

public class UserCreateController extends HttpController {

    @Override
    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        new UserService().addUser(UserResolver.resolve(httpRequest));

        httpResponse.addHeader("Location", "/index.html");
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.sendRedirect();
    }
}
