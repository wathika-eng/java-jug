package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "community", layout = MainLayout.class)
@PageTitle("Community — Kenya JUG")
@AnonymousAllowed
public class CommunityView extends StaticPage {

    public CommunityView() {
        super("community");
    }
}
