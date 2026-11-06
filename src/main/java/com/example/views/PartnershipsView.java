package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "partnerships", layout = MainLayout.class)
@PageTitle("Partnerships — Kenya JUG")
@AnonymousAllowed
public class PartnershipsView extends StaticPage {

    public PartnershipsView() {
        super("partnerships");
    }
}
