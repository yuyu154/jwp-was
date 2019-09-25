package http.request;

import java.util.List;

public class HttpRequestMaker {
    private static final String DELIMITER_OF_HEADER = ":";

    public static HttpRequest parseInputs(final List<String> inputs) {
        HttpRequestStartLine httpRequestStartLine = new HttpRequestStartLine(inputs.get(0));

    }
}
