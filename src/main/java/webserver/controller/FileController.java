package webserver.controller;

import http.HttpRequest;
import http.HttpResponse;
import http.support.resource.ResourceType;
import http.support.resource.ResourceTypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.support.PathHandler;

import java.io.IOException;
import java.net.URISyntaxException;

public class FileController extends HttpController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Override
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException, URISyntaxException {
        String url = httpRequest.getResourcePath();
        log.debug("url in FileController : {}", url);

        String absoluteUrl = PathHandler.path(url);
        ResourceType resourceType = ResourceTypeFactory.getInstance(absoluteUrl);
        httpResponse.addHeader("Content-Type", resourceType.getResourceType());
        httpResponse.forward(absoluteUrl);
    }
}
