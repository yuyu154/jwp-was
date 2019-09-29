package servlet.view;

import com.github.jknack.handlebars.Template;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class View {
    private final Template templateView;
    private final Map<String, Object> model = new HashMap<>();

    public View(final Template templateView) {
        this.templateView = templateView;
    }

    public void addModel(final String key, final Object value) {
        model.put(key, value);
    }

    public byte[] render(final Charset charset) throws IOException {
        return templateView.apply(model).getBytes(charset);
    }
}
