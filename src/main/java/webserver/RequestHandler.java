package webserver;

import http.HttpResponse;
import http.request.HttpRequest;
import http.request.HttpRequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.controller.Controller;
import webserver.controller.ControllerFinder;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private final ControllerFinder controllerFinder;

    private Socket connection;

    public RequestHandler(Socket connectionSocket, ControllerFinder controllerFinder) {
        this.connection = connectionSocket;
        this.controllerFinder = controllerFinder;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader bufferedReader = getBufferedReader(in);

            HttpRequest httpRequest = HttpRequestFactory.create(bufferedReader);
            HttpResponse httpResponse = new HttpResponse(new DataOutputStream(out));

            // @TODO 계층을 하나 추가하는 것 고려하기
            Controller controller = controllerFinder.find(httpRequest);
            controller.service(httpRequest, httpResponse);
        } catch (IOException | URISyntaxException e) {
            logger.error(e.getMessage());
        }
    }

    private BufferedReader getBufferedReader(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }
}
