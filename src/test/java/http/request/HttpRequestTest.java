package http.request;

import http.request.HttpRequest;
import http.request.HttpRequestBody;
import http.request.HttpRequestHeader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testhelper.Common;
import utils.IOUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestTest {

    @Test
    @DisplayName("HTTP GET 요청")
    public void httpGetRequest() throws IOException {
        List<String> inputs = IOUtils.parseAllLine(Common.getBufferedReader("HTTP_GET_INDEX_HTML.txt"));
        HttpRequest httpRequest = HttpRequestMaker.parseInputs(inputs);

        assertThat(httpRequest.getMethod()).isEqualTo("GET");
        assertThat(httpRequest.getResourcePath()).isEqualTo("/user/create");
        assertThat(httpRequest.getHeader("Host")).isEqualTo("localhost:8080");
        assertThat(httpRequest.getParameter("password")).isEqualTo("password");
    }

    @Test
    @DisplayName("HTTP POST 요청")
    public void httpPostRequest() {
        List<String> inputs = Arrays.asList(
                "POST /user/create HTTP/1.1",
                "Host: localhost:8080",
                "Connection: keep-alive",
                "Content-Length: 46",
                "Content-Type: application/x-www-form-urlencoded",
                "Accept: */*");
        String body = "userId=javajigi&password=password";

        HttpRequestHeader httpRequestHeader = new HttpRequestHeader(inputs);
        HttpRequestBody httpRequestBody = new HttpRequestBody(body);
        HttpRequest httpRequest = new HttpRequest(httpRequestHeader, httpRequestBody);

        assertThat(httpRequest.getMethod()).isEqualTo("POST");
        assertThat(httpRequest.getResourcePath()).isEqualTo("/user/create");
        assertThat(httpRequest.getHeader("Content-Length")).isEqualTo("46");
        assertThat(httpRequest.getHeader("Content-Type")).isEqualTo("application/x-www-form-urlencoded");
        assertThat(httpRequest.getParameter("password")).isEqualTo("password");
    }
}