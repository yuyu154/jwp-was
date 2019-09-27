package servlet.controller;

import http.response.HttpResponse;
import http.request.HttpRequest;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Controller {
    void service(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException, URISyntaxException;
}