package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "events/java-connect-ke", layout = MainLayout.class)
@PageTitle("Java Connect KE — Kenya JUG")
@AnonymousAllowed
public class JavaConnectKeView extends StaticPage {

    public JavaConnectKeView() {
        super("events-java-connect-ke");
    }
}
