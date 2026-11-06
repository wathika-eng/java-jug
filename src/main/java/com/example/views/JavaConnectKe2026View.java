package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "events/java-connect-ke/2026", layout = MainLayout.class)
@PageTitle("Java Connect KE 2026 — Kenya JUG")
@AnonymousAllowed
public class JavaConnectKe2026View extends StaticPage {

    public JavaConnectKe2026View() {
        super("events-java-connect-ke-2026");
    }
}
