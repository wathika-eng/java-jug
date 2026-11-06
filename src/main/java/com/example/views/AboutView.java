package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "about", layout = MainLayout.class)
@PageTitle("About — Kenya JUG")
@AnonymousAllowed
public class AboutView extends StaticPage {

    public AboutView() {
        super("about");
    }
}
