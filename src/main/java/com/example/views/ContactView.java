package com.example.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "contact", layout = MainLayout.class)
@PageTitle("Contact | Kenya JUG")
@AnonymousAllowed
public class ContactView extends VerticalLayout {

    public ContactView() {
        setPadding(false);
        setSpacing(false);
        build();
    }

    private void build() {
        Section section = new Section();
        section.setWidthFull();
        section.getStyle().set("padding", "var(--vaadin-padding-xl) var(--vaadin-padding-l)");

        H1 title = new H1("Contact Us");
        title.getStyle().set("text-align", "center");

        Paragraph subtitle = new Paragraph("Get in touch with the Kenya JUG team.");
        subtitle.getStyle()
                .set("text-align", "center")
                .set("color", "var(--vaadin-text-color-secondary)")
                .set("margin-bottom", "var(--vaadin-padding-xl)");

        HorizontalLayout content = new HorizontalLayout();
        content.setWidthFull();
        content.setSpacing(true);
        content.setWrap(true);

        // Contact Info
        VerticalLayout infoPanel = new VerticalLayout();
        infoPanel.setFlexGrow(1);
        infoPanel.setSpacing(true);

        H2 infoTitle = new H2("Get in Touch");
        infoTitle.getStyle().set("margin-bottom", "0");

        Paragraph infoText = new Paragraph(
                "Have a question, suggestion, or want to collaborate? We'd love to hear from you. " +
                "Fill out the form or reach us through our social media channels.");
        infoText.getStyle().set("color", "var(--vaadin-text-color-secondary)");

        Div contactDetails = new Div();
        contactDetails.getStyle().set("margin-top", "var(--vaadin-padding-m)");

        Paragraph emailP = new Paragraph("✉️ info@kenyajug.org");
        Paragraph twitterP = new Paragraph("🐦 @kenyanJug on Twitter");
        Paragraph linkedinP = new Paragraph("💼 Kenya JUG on LinkedIn");
        contactDetails.add(emailP, twitterP, linkedinP);
        contactDetails.getChildren().forEach(c ->
                c.getStyle().set("color", "var(--vaadin-text-color-secondary)"));

        infoPanel.add(infoTitle, infoText, contactDetails);

        // Contact Form
        VerticalLayout formPanel = new VerticalLayout();
        formPanel.setFlexGrow(1);
        formPanel.setSpacing(true);
        formPanel.getStyle()
                .set("border", "1px solid var(--vaadin-border-color)")
                .set("border-radius", "var(--vaadin-radius-l)")
                .set("padding", "var(--vaadin-padding-l)");

        TextField nameField = new TextField("Full Name *");
        nameField.setRequired(true);
        nameField.setRequiredIndicatorVisible(true);

        TextField emailField = new TextField("Email *");
        emailField.setRequired(true);
        emailField.setRequiredIndicatorVisible(true);
        emailField.getElement().setAttribute("type", "email");

        TextField subjectField = new TextField("Subject");

        TextArea messageField = new TextArea("Message *");
        messageField.setRequired(true);
        messageField.setRequiredIndicatorVisible(true);
        messageField.getElement().setAttribute("rows", "5");

        Button sendBtn = new Button("Send Message", e -> {
            if (nameField.isEmpty() || emailField.isEmpty() || messageField.isEmpty()) {
                Notification.show("Please fill in all required fields.", 3000, Notification.Position.MIDDLE);
                return;
            }
            String emailValue = emailField.getValue();
            if (!emailValue.contains("@") || !emailValue.contains(".")) {
                Notification.show("Please enter a valid email address.", 3000, Notification.Position.MIDDLE);
                return;
            }
            // In production, this would send an email to admin
            Notification.show("Thank you for your message. We will get back to you soon.", 5000, Notification.Position.MIDDLE);
            nameField.clear();
            emailField.clear();
            subjectField.clear();
            messageField.clear();
        });
        sendBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        formPanel.add(nameField, emailField, subjectField, messageField, sendBtn);

        content.add(infoPanel, formPanel);
        section.add(title, subtitle, content);
        add(section);
    }
}
