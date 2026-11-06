package com.example.admin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
public class AdminLayout extends VerticalLayout
        implements RouterLayout, BeforeEnterObserver {

    public void showRouterLayoutContent(Component content) {
        this.removeAll();
        this.addClassNames("admin-layout");
        this.setPadding(false);
        this.setSpacing(false);
        this.setWidthFull();
        this.getStyle().set("min-height", "100vh");

        HorizontalLayout main = new HorizontalLayout();
        main.setWidthFull();
        main.setPadding(false);
        main.setSpacing(false);
        main.getStyle().set("min-height", "100vh");

        // Sidebar
        Nav sidebar = createSidebar();
        sidebar.setWidth("250px");
        sidebar.getStyle()
                .set("background-color", "var(--vaadin-background-container)")
                .set("border-right", "1px solid var(--vaadin-border-color-secondary)")
                .set("flex-shrink", "0");

        // Content
        Div contentDiv = new Div();
        contentDiv.getStyle().set("flex", "1").set("padding", "var(--vaadin-padding-l)");
        contentDiv.add(content);

        main.add(sidebar, contentDiv);
        add(main);
    }

    private Nav createSidebar() {
        Nav nav = new Nav();
        nav.setWidthFull();

        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setPadding(true);
        sidebar.setSpacing(true);
        sidebar.setWidthFull();

        H2 title = new H2("Admin");
        title.getStyle()
                .set("margin", "0")
                .set("font-size", "var(--aura-font-size-l)")
                .set("color", "var(--aura-accent-color-light, #0057b8)");

        VerticalLayout navItems = new VerticalLayout();
        navItems.setSpacing(false);
        navItems.add(createNavLink("Events", "/admin/events"));
        navItems.add(createNavLink("Team", "/admin/team"));
        navItems.add(createNavLink("Speakers", "/admin/speakers"));
        navItems.add(createNavLink("Resources", "/admin/resources"));

        Anchor homeLink = new Anchor("/", "← Back to Site");
        homeLink.getStyle()
                .set("display", "block")
                .set("margin-top", "auto")
                .set("padding-top", "var(--vaadin-padding-m)")
                .set("border-top", "1px solid var(--vaadin-border-color-secondary)")
                .set("color", "var(--vaadin-text-color-secondary)");

        sidebar.add(title, navItems);
        sidebar.setFlexGrow(1, navItems);
        sidebar.add(homeLink);

        nav.add(sidebar);
        return nav;
    }

    private Anchor createNavLink(String text, String href) {
        Anchor link = new Anchor(href, text);
        link.getStyle()
                .set("padding", "var(--vaadin-padding-xs) var(--vaadin-padding-s)")
                .set("border-radius", "var(--vaadin-radius-s)")
                .set("text-decoration", "none");
        return link;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
    }
}
