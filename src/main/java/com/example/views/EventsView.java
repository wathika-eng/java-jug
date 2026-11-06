package com.example.views;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "events", layout = MainLayout.class)
@PageTitle("Events — Kenya JUG")
@AnonymousAllowed
public class EventsView extends StaticPage {

    public EventsView() {
        super("events");
    }
}
