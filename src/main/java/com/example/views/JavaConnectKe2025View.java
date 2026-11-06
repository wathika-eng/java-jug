package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "events/java-connect-ke/2025", layout = MainLayout.class)
@PageTitle("Java Connect KE 2025 — Kenya JUG")
@AnonymousAllowed
public class JavaConnectKe2025View extends StaticPage {

    public JavaConnectKe2025View() {
        super("events-java-connect-ke-2025");
    }
}
