package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "resources", layout = MainLayout.class)
@PageTitle("Resources — Kenya JUG")
@AnonymousAllowed
public class ResourcesView extends StaticPage {

    public ResourcesView() {
        super("resources");
    }
}
