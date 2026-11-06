package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "home", layout = MainLayout.class)
@PageTitle("Kenya JUG — Kenya Java User Group")
@AnonymousAllowed
public class HomeView extends StaticPage {

    public HomeView() {
        super("home");
    }
}
