package com.example.views;

import com.example.team.TeamMember;
import com.example.team.TeamMemberService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import java.util.List;

@Route(value = "team", layout = MainLayout.class)
@PageTitle("Team | Kenya JUG")
@AnonymousAllowed
public class TeamView extends VerticalLayout {

    private final TeamMemberService teamMemberService;

    public TeamView(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
        setPadding(false);
        setSpacing(false);
        build();
    }

    private void build() {
        Section section = new Section();
        section.setWidthFull();
        section.getStyle().set("padding", "var(--vaadin-padding-xl) var(--vaadin-padding-l)");

        H1 title = new H1("Kenya JUG Team");
        title.getStyle().set("text-align", "center");

        com.vaadin.flow.component.html.Paragraph subtitle = new com.vaadin.flow.component.html.Paragraph("Meet the people who make Kenya JUG possible.");
        subtitle.getStyle()
                .set("text-align", "center")
                .set("color", "var(--vaadin-text-color-secondary)")
                .set("margin-bottom", "var(--vaadin-padding-xl)");

        List<TeamMember> members = teamMemberService.getActiveMembers();
        HorizontalLayout cards = new HorizontalLayout();
        cards.setWidthFull();
        cards.setSpacing(true);
        cards.setWrap(true);
        cards.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        if (members.isEmpty()) {
            Span noMembers = new Span("Team information coming soon.");
            noMembers.getStyle()
                    .set("text-align", "center")
                    .set("width", "100%")
                    .set("color", "var(--vaadin-text-color-secondary)");
            cards.add(noMembers);
        } else {
            for (TeamMember member : members) {
                cards.add(createMemberCard(member));
            }
        }

        section.add(title, subtitle, cards);
        add(section);
    }

    private Article createMemberCard(TeamMember member) {
        Article card = new Article();
        card.addClassNames("team-card");
        card.setWidth("280px");
        card.getStyle()
                .set("border", "1px solid var(--vaadin-border-color)")
                .set("border-radius", "var(--vaadin-radius-l)")
                .set("overflow", "hidden")
                .set("text-align", "center");

        // Avatar placeholder
        Div avatar = new Div();
        avatar.getStyle()
                .set("width", "100px")
                .set("height", "100px")
                .set("border-radius", "50%")
                .set("background", "linear-gradient(135deg, #0057b8 0%, #003d82 100%)")
                .set("margin", "var(--vaadin-padding-m) auto 0")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("color", "white")
                .set("font-size", "var(--aura-font-size-xl)")
                .set("font-weight", "var(--aura-font-weight-semibold)");
        avatar.setText(member.getName().charAt(0) + "");

        Div content = new Div();
        content.getStyle().set("padding", "var(--vaadin-padding-m)");

        H3 name = new H3(member.getName());
        name.getStyle()
                .set("margin", "var(--vaadin-padding-xs) 0 0")
                .set("font-size", "var(--aura-font-size-m)");

        Span role = new Span(member.getRole());
        role.getStyle()
                .set("color", "var(--aura-accent-color-light, #0057b8)")
                .set("font-size", "var(--aura-font-size-s)")
                .set("font-weight", "var(--aura-font-weight-medium)");

        content.add(name, role);

        if (member.getBio() != null && !member.getBio().isEmpty()) {
            com.vaadin.flow.component.html.Paragraph bio = new com.vaadin.flow.component.html.Paragraph(member.getBio());
            bio.getStyle()
                    .set("color", "var(--vaadin-text-color-secondary)")
                    .set("font-size", "var(--aura-font-size-s)")
                    .set("margin-top", "var(--vaadin-padding-s)")
                    .set("line-height", "1.5");
            content.add(bio);
        }

        card.add(avatar, content);
        return card;
    }
}
