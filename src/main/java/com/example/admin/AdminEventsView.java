package com.example.admin;

import com.example.events.Event;
import com.example.events.EventService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Route(value = "admin/events", layout = AdminLayout.class)
@PageTitle("Manage Events | Admin")
@RolesAllowed("ADMIN")
public class AdminEventsView extends VerticalLayout {

    private final EventService eventService;
    private final Grid<Event> grid = new Grid<>(Event.class, false);

    public AdminEventsView(EventService eventService) {
        this.eventService = eventService;
        setSpacing(true);
        build();
    }

    private void build() {
        H1 title = new H1("Manage Events");

        Button addBtn = new Button("+ Add Event", e -> showForm(null));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setWidthFull();
        grid.addColumn(Event::getTitle).setHeader("Title").setFlexGrow(2);
        grid.addColumn(e -> e.getDate() != null ? e.getDate().toString() : "").setHeader("Date").setFlexGrow(1);
        grid.addColumn(Event::getLocation).setHeader("Location").setFlexGrow(1);
        grid.addColumn(Event::getType).setHeader("Type").setFlexGrow(1);
        grid.addColumn(Event::getStatus).setHeader("Status").setFlexGrow(1);

        grid.addComponentColumn(e -> {
            HorizontalLayout btns = new HorizontalLayout();
            btns.setSpacing(true);
            Button editBtn = new Button("Edit", click -> showForm(e));
            Button deleteBtn = new Button("Delete", click -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Delete Event");
                dialog.add("Are you sure you want to delete this event?");
                dialog.setConfirmText("Delete");
                dialog.setCancelable(true);
                dialog.addConfirmListener(c -> {
                    eventService.deleteEvent(e.getId());
                    refresh();
                    Notification.show("Event deleted.", 3000, Notification.Position.MIDDLE);
                });
                dialog.open();
            });
            btns.add(editBtn, deleteBtn);
            return btns;
        }).setHeader("Actions").setFlexGrow(1);

        refresh();
        add(title, addBtn, grid);
    }

    private void showForm(Event event) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(event != null ? "Edit Event" : "Add Event");

        TextField titleField = new TextField("Title *");
        titleField.setRequired(true);
        TextArea descField = new TextArea("Description");
        DatePicker dateField = new DatePicker("Date *");
        dateField.setRequired(true);
        TimePicker timeField = new TimePicker("Time");
        TextField locationField = new TextField("Location *");
        locationField.setRequired(true);

        com.vaadin.flow.component.combobox.ComboBox<String> typeField =
                new com.vaadin.flow.component.combobox.ComboBox<>("Type");
        typeField.setItems("MEETUP", "CONFERENCE");
        typeField.setRequired(true);

        com.vaadin.flow.component.combobox.ComboBox<String> statusField =
                new com.vaadin.flow.component.combobox.ComboBox<>("Status");
        statusField.setItems("SCHEDULED", "ONGOING", "COMPLETED", "CANCELLED");

        if (event != null) {
            titleField.setValue(event.getTitle());
            descField.setValue(event.getDescription());
            dateField.setValue(event.getDate());
            timeField.setValue(event.getTime());
            locationField.setValue(event.getLocation());
            typeField.setValue(event.getType());
            statusField.setValue(event.getStatus());
        }

        Button saveBtn = new Button("Save", e -> {
            if (titleField.isEmpty() || dateField.getValue() == null || locationField.isEmpty() || typeField.isEmpty()) {
                Notification.show("Please fill in all required fields.", 3000, Notification.Position.MIDDLE);
                return;
            }
            Event ev = event != null ? event : new Event();
            ev.setTitle(titleField.getValue());
            ev.setDescription(descField.getValue());
            ev.setDate(dateField.getValue());
            ev.setTime(timeField.getValue());
            ev.setLocation(locationField.getValue());
            ev.setType(typeField.getValue());
            ev.setStatus(statusField.getValue() != null ? statusField.getValue() : "SCHEDULED");
            eventService.saveEvent(ev);
            dialog.close();
            refresh();
            Notification.show("Event saved.", 3000, Notification.Position.MIDDLE);
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        VerticalLayout form = new VerticalLayout(
                titleField, descField, dateField, timeField, locationField, typeField, statusField,
                new HorizontalLayout(saveBtn, cancelBtn));
        form.setSpacing(true);
        dialog.add(form);
        dialog.open();
    }

    private void refresh() {
        List<Event> events = eventService.getAllEvents();
        grid.setItems(events);
    }
}
