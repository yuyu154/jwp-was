package webserver.controller;

import http.HttpRequest;
import http.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Controller {
    void service(HttpRequest httpRequest, Response response) throws IOException, URISyntaxException;
}