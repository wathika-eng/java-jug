package com.example.admin;

import com.example.speakers.Speaker;
import com.example.speakers.SpeakerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;

@Route(value = "admin/speakers", layout = AdminLayout.class)
@PageTitle("Manage Speakers | Admin")
@RolesAllowed("ADMIN")
public class AdminSpeakersView extends VerticalLayout {

    private final SpeakerService service;
    private final Grid<Speaker> grid = new Grid<>(Speaker.class, false);

    public AdminSpeakersView(SpeakerService service) {
        this.service = service;
        setSpacing(true);
        build();
    }

    private void build() {
        H1 title = new H1("Manage Speakers");
        Button addBtn = new Button("+ Add Speaker", e -> showForm(null));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setWidthFull();
        grid.addColumn(Speaker::getName).setHeader("Name").setFlexGrow(1);
        grid.addColumn(Speaker::getTitle).setHeader("Title").setFlexGrow(1);
        grid.addColumn(Speaker::getEmail).setHeader("Email").setFlexGrow(1);

        grid.addComponentColumn(s -> {
            HorizontalLayout btns = new HorizontalLayout();
            btns.setSpacing(true);
            Button editBtn = new Button("Edit", click -> showForm(s));
            Button deleteBtn = new Button("Delete", click -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Delete");
                dialog.add("Delete this speaker? Sessions will be unassigned.");
                dialog.setConfirmText("Delete");
                dialog.setCancelable(true);
                dialog.addConfirmListener(c -> { service.deleteSpeaker(s.getId()); refresh(); Notification.show("Deleted.", 3000, Notification.Position.MIDDLE); });
                dialog.open();
            });
            btns.add(editBtn, deleteBtn);
            return btns;
        }).setHeader("Actions").setFlexGrow(1);

        refresh();
        add(title, addBtn, grid);
    }

    private void showForm(Speaker speaker) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(speaker != null ? "Edit Speaker" : "Add Speaker");

        TextField nameField = new TextField("Name *");
        nameField.setRequired(true);
        TextField titleField = new TextField("Title *");
        titleField.setRequired(true);
        TextArea bioField = new TextArea("Bio");
        TextField emailField = new TextField("Email");
        TextField socialField = new TextField("Social Links");

        if (speaker != null) {
            nameField.setValue(speaker.getName());
            titleField.setValue(speaker.getTitle());
            bioField.setValue(speaker.getBio());
            emailField.setValue(speaker.getEmail());
            socialField.setValue(speaker.getSocialLinks());
        }

        Button saveBtn = new Button("Save", e -> {
            if (nameField.isEmpty() || titleField.isEmpty()) {
                Notification.show("Name and title are required.", 3000, Notification.Position.MIDDLE);
                return;
            }
            Speaker s = speaker != null ? speaker : new Speaker();
            s.setName(nameField.getValue());
            s.setTitle(titleField.getValue());
            s.setBio(bioField.getValue());
            s.setEmail(emailField.getValue());
            s.setSocialLinks(socialField.getValue());
            service.saveSpeaker(s);
            dialog.close();
            refresh();
            Notification.show("Saved.", 3000, Notification.Position.MIDDLE);
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        VerticalLayout form = new VerticalLayout(nameField, titleField, bioField, emailField, socialField,
                new HorizontalLayout(saveBtn, cancelBtn));
        form.setSpacing(true);
        dialog.add(form);
        dialog.open();
    }

    private void refresh() {
        grid.setItems(service.getAllSpeakers());
    }
}
