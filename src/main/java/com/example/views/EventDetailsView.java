package com.example.views;

import com.example.events.Event;
import com.example.events.EventService;
import com.example.events.EventSession;
import com.example.registrations.RegistrationService;
import com.example.speakers.Speaker;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Route(value = "event/:id", layout = MainLayout.class)
@PageTitle("Event Details | Kenya JUG")
@AnonymousAllowed
public class EventDetailsView extends VerticalLayout implements BeforeEnterObserver {

    private final EventService eventService;
    private final RegistrationService registrationService;
    private String eventId;

    public EventDetailsView(EventService eventService, RegistrationService registrationService) {
        this.eventService = eventService;
        this.registrationService = registrationService;
        setPadding(false);
        setSpacing(false);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        eventId = event.getRouteParameters().get("id").orElse("1");
        build(Long.parseLong(eventId));
    }

    private void build(Long id) {
        Optional<Event> opt = eventService.getEventById(id);
        if (opt.isEmpty()) {
            Div notFound = new Div();
            notFound.setWidthFull();
            notFound.getStyle()
                    .set("padding", "var(--vaadin-padding-xl)")
                    .set("text-align", "center");
            H1 h1 = new H1("Event Not Found");
            com.vaadin.flow.component.html.Paragraph p = new com.vaadin.flow.component.html.Paragraph("The event you are looking for does not exist or has been removed.");
            com.vaadin.flow.component.html.Anchor back = new com.vaadin.flow.component.html.Anchor("events", "← Back to Events");
            notFound.add(h1, p, back);
            add(notFound);
            return;
        }

        Event event = opt.get();

        // Banner
        Section banner = new Section();
        banner.setWidthFull();
        banner.getStyle()
                .set("background", "linear-gradient(135deg, #0057b8 0%, #003d82 100%)")
                .set("color", "white")
                .set("padding", "var(--vaadin-padding-xl) var(--vaadin-padding-l)");

        VerticalLayout bannerContent = new VerticalLayout();
        bannerContent.setSpacing(true);
        bannerContent.setWidthFull();

        Span typeBadge = new Span(event.getType().equals("CONFERENCE") ? "Conference" : "Meetup");
        typeBadge.getStyle()
                .set("font-size", "var(--aura-font-size-xs)")
                .set("padding", "2px 8px")
                .set("border-radius", "var(--vaadin-radius-s)")
                .set("background-color", "rgba(255,255,255,0.2)")
                .set("color", "white");

        H1 title = new H1(event.getTitle());
        title.getStyle().set("color", "white").set("margin", "0");

        HorizontalLayout meta = new HorizontalLayout();
        meta.setSpacing(true);
        meta.add(new Span("📅 " + formatDate(event.getDate())));
        if (event.getTime() != null) {
            meta.add(new Span("🕐 " + event.getTime().format(DateTimeFormatter.ofPattern("HH:mm"))));
        }
        meta.add(new Span("📍 " + event.getLocation()));
        meta.getChildren().forEach(c -> c.getStyle().set("color", "rgba(255,255,255,0.9)"));

        bannerContent.add(typeBadge, title, meta);
        banner.add(bannerContent);

        // Description
        Section description = new Section();
        description.setWidthFull();
        description.getStyle().set("padding", "var(--vaadin-padding-xl) var(--vaadin-padding-l)");

        H2 descTitle = new H2("About This Event");
        Paragraph descText = new Paragraph(event.getDescription() != null ? event.getDescription() : "No description available.");
        descText.getStyle().set("color", "var(--vaadin-text-color-secondary)");
        description.add(descTitle, descText);

        // Sessions
        List<EventSession> sessions = event.getSessions();
        if (!sessions.isEmpty()) {
            Section sessionsSection = new Section();
            sessionsSection.setWidthFull();
            sessionsSection.getStyle()
                    .set("padding", "0 var(--vaadin-padding-l) var(--vaadin-padding-xl)");

            H2 sessionsTitle = new H2("Sessions");
            VerticalLayout sessionsList = new VerticalLayout();
            sessionsList.setSpacing(true);

            for (EventSession session : sessions) {
                Article sessionCard = new Article();
                sessionCard.getStyle()
                        .set("border", "1px solid var(--vaadin-border-color)")
                        .set("border-radius", "var(--vaadin-radius-m)")
                        .set("padding", "var(--vaadin-padding-m)");

                H3 sessionTitle = new H3(session.getTitle());
                sessionTitle.getStyle().set("margin", "0 0 var(--vaadin-padding-xs) 0");

                Span time = new Span(
                        session.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " +
                        session.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                time.getStyle().set("color", "var(--vaadin-text-color-secondary)");

                if (session.getDescription() != null) {
                Paragraph sessionDesc = new Paragraph(session.getDescription());
                    sessionDesc.getStyle().set("color", "var(--vaadin-text-color-secondary)");
                }

                if (session.getSpeaker() != null) {
                    Speaker speaker = session.getSpeaker();
                    Div speakerDiv = new Div();
                    speakerDiv.getStyle().set("margin-top", "var(--vaadin-padding-s)");
                    H4 speakerName = new H4(speaker.getName());
                    speakerName.getStyle().set("margin", "0");
                    Span speakerTitle = new Span(speaker.getTitle());
                    speakerTitle.getStyle().set("color", "var(--vaadin-text-color-secondary)");
                    speakerDiv.add(speakerName, speakerTitle);
                    sessionCard.add(sessionTitle, time, speakerDiv);
                } else {
                    sessionCard.add(sessionTitle, time);
                }

                sessionsList.add(sessionCard);
            }

            sessionsSection.add(sessionsTitle, sessionsList);
            add(banner, description, sessionsSection);
        } else {
            add(banner, description);
        }

        // Register button
        Section registerSection = new Section();
        registerSection.setWidthFull();
        registerSection.getStyle()
                .set("padding", "var(--vaadin-padding-l)")
                .set("text-align", "center");

        Button registerBtn = new Button("Register for this Event", e -> showRegistrationDialog(event));
        registerBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerSection.add(registerBtn);
        add(registerSection);

        // Back link
        com.vaadin.flow.component.html.Anchor back = new com.vaadin.flow.component.html.Anchor("events", "← Back to Events");
        back.getStyle().set("display", "block").set("text-align", "center").set("padding", "var(--vaadin-padding-m)");
        add(back);
    }

    private void showRegistrationDialog(Event event) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Register for " + event.getTitle());

        TextField nameField = new TextField("Full Name *");
        nameField.setRequired(true);
        nameField.setRequiredIndicatorVisible(true);

        TextField emailField = new TextField("Email *");
        emailField.setRequired(true);
        emailField.setRequiredIndicatorVisible(true);
        emailField.getElement().setAttribute("type", "email");

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        Button cancelBtn = new Button("Cancel", e -> dialog.close());
        Button submitBtn = new Button("Register", e -> {
            if (nameField.isEmpty() || emailField.isEmpty()) {
                Notification.show("Please fill in all required fields.", 3000, Notification.Position.MIDDLE);
                return;
            }
            String email = emailField.getValue();
            if (!email.contains("@") || !email.contains(".")) {
                Notification.show("Please enter a valid email address.", 3000, Notification.Position.MIDDLE);
                return;
            }

            Optional<com.example.registrations.Registration> existing =
                    registrationService.findByEventAndEmail(event, email);
            if (existing.isPresent()) {
                Notification.show("This email is already registered for this event.", 3000, Notification.Position.MIDDLE);
                return;
            }

            try {
                registrationService.register(event, nameField.getValue(), email);
                Notification.show("Registration confirmed! You will receive event details at your email.", 5000, Notification.Position.MIDDLE);
                dialog.close();
            } catch (Exception ex) {
                if (ex.getMessage() != null && ex.getMessage().contains("unique")) {
                    Notification.show("This email is already registered for this event.", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("Registration failed. Please try again.", 3000, Notification.Position.MIDDLE);
                }
            }
        });
        submitBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttons.add(cancelBtn, submitBtn);
        VerticalLayout form = new VerticalLayout(nameField, emailField, buttons);
        form.setSpacing(true);
        dialog.add(form);
        dialog.open();
    }

    private String formatDate(java.time.LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
    }
}
