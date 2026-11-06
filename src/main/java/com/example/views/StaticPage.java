package com.example.views;

import com.vaadin.flow.component.html.Main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Base class for the public pages that reproduce the Kenya JUG target site.
 *
 * <p>The original site's markup is kept verbatim in {@code src/main/resources/pages}
 * so the rendered result stays pixel-identical to the reference. Each concrete view
 * only needs to point at its fragment and declare its route.</p>
 */
public abstract class StaticPage extends Main {

    protected StaticPage(String pageName) {
        addClassName("flex-1");
        getElement().setProperty("innerHTML", loadPage(pageName));
    }

    protected static String loadPage(String pageName) {
        String resource = "/pages/" + pageName + ".html";
        try (InputStream in = StaticPage.class.getResourceAsStream(resource)) {
            if (in == null) {
                throw new IllegalStateException("Missing page fragment: " + resource);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read page fragment: " + resource, e);
        }
    }
}
