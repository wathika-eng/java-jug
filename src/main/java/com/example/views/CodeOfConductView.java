package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "code-of-conduct", layout = MainLayout.class)
@PageTitle("Code of Conduct — Kenya JUG")
@AnonymousAllowed
public class CodeOfConductView extends StaticPage {

    public CodeOfConductView() {
        super("code-of-conduct");
    }
}
