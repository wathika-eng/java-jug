package com.example;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.theme.aura.Aura;

@StyleSheet(Aura.STYLESHEET)
@StyleSheet("styles.css")
@Push
@Viewport("width=device-width, initial-scale=1.0")
@Meta(name = "theme-color", content = "#1c1e19")
public class AppShell implements AppShellConfigurator {

    @Override
    public void configurePage(AppShellSettings settings) {
        // Add favicon
        settings.addFavIcon("/favicon.ico", "icon", "48x48");
        settings.addFavIcon("/apple-icon.png", "apple-touch-icon", "180x180");
        // Add Google Fonts
        settings.addLink(
                "https://fonts.googleapis.com/css2?family=Fraunces:wght@400;500;600;700&family=IBM+Plex+Sans:wght@400;500;600&family=JetBrains+Mono:wght@400;500&display=swap",
                "stylesheet");
    }
}