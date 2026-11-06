package com.example.admin;

import com.example.resources.Resource;
import com.example.resources.ResourceService;
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

@Route(value = "admin/resources", layout = AdminLayout.class)
@PageTitle("Manage Resources | Admin")
@RolesAllowed("ADMIN")
public class AdminResourcesView extends VerticalLayout {

    private final ResourceService service;
    private final Grid<Resource> grid = new Grid<>(Resource.class, false);

    public AdminResourcesView(ResourceService service) {
        this.service = service;
        setSpacing(true);
        build();
    }

    private void build() {
        H1 title = new H1("Manage Resources");
        Button addBtn = new Button("+ Add Resource", e -> showForm(null));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setWidthFull();
        grid.addColumn(Resource::getTitle).setHeader("Title").setFlexGrow(1);
        grid.addColumn(Resource::getCategory).setHeader("Category").setFlexGrow(1);
        grid.addColumn(Resource::getResourceType).setHeader("Type").setFlexGrow(1);
        grid.addColumn(Resource::getUrl).setHeader("URL").setFlexGrow(2);

        grid.addComponentColumn(r -> {
            HorizontalLayout btns = new HorizontalLayout();
            btns.setSpacing(true);
            Button editBtn = new Button("Edit", click -> showForm(r));
            Button deleteBtn = new Button("Delete", click -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Delete");
                dialog.add("Delete this resource?");
                dialog.setConfirmText("Delete");
                dialog.setCancelable(true);
                dialog.addConfirmListener(c -> { service.deleteResource(r.getId()); refresh(); Notification.show("Deleted.", 3000, Notification.Position.MIDDLE); });
                dialog.open();
            });
            btns.add(editBtn, deleteBtn);
            return btns;
        }).setHeader("Actions").setFlexGrow(1);

        refresh();
        add(title, addBtn, grid);
    }

    private void showForm(Resource resource) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(resource != null ? "Edit Resource" : "Add Resource");

        TextField titleField = new TextField("Title *");
        titleField.setRequired(true);
        TextArea descField = new TextArea("Description");
        TextField urlField = new TextField("URL *");
        urlField.setRequired(true);
        TextField categoryField = new TextField("Category *");
        categoryField.setRequired(true);

        com.vaadin.flow.component.combobox.ComboBox<String> typeField =
                new com.vaadin.flow.component.combobox.ComboBox<>("Type");
        typeField.setItems("TUTORIAL", "TOOL", "DOCUMENTATION", "OTHER");
        typeField.setRequired(true);

        if (resource != null) {
            titleField.setValue(resource.getTitle());
            descField.setValue(resource.getDescription());
            urlField.setValue(resource.getUrl());
            categoryField.setValue(resource.getCategory());
            typeField.setValue(resource.getResourceType());
        }

        Button saveBtn = new Button("Save", e -> {
            if (titleField.isEmpty() || urlField.isEmpty() || categoryField.isEmpty() || typeField.isEmpty()) {
                Notification.show("Title, URL, category, and type are required.", 3000, Notification.Position.MIDDLE);
                return;
            }
            Resource r = resource != null ? resource : new Resource();
            r.setTitle(titleField.getValue());
            r.setDescription(descField.getValue());
            r.setUrl(urlField.getValue());
            r.setCategory(categoryField.getValue());
            r.setResourceType(typeField.getValue());
            r.setActive(true);
            service.saveResource(r);
            dialog.close();
            refresh();
            Notification.show("Saved.", 3000, Notification.Position.MIDDLE);
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        VerticalLayout form = new VerticalLayout(titleField, descField, urlField, categoryField, typeField,
                new HorizontalLayout(saveBtn, cancelBtn));
        form.setSpacing(true);
        dialog.add(form);
        dialog.open();
    }

    private void refresh() {
        grid.setItems(service.getAllResources());
    }
}
