package com.example.admin;

import com.example.team.TeamMember;
import com.example.team.TeamMemberService;
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

@Route(value = "admin/team", layout = AdminLayout.class)
@PageTitle("Manage Team | Admin")
@RolesAllowed("ADMIN")
public class AdminTeamView extends VerticalLayout {

    private final TeamMemberService service;
    private final Grid<TeamMember> grid = new Grid<>(TeamMember.class, false);

    public AdminTeamView(TeamMemberService service) {
        this.service = service;
        setSpacing(true);
        build();
    }

    private void build() {
        H1 title = new H1("Manage Team Members");
        Button addBtn = new Button("+ Add Member", e -> showForm(null));
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setWidthFull();
        grid.addColumn(TeamMember::getName).setHeader("Name").setFlexGrow(1);
        grid.addColumn(TeamMember::getRole).setHeader("Role").setFlexGrow(1);
        grid.addColumn(TeamMember::getEmail).setHeader("Email").setFlexGrow(1);
        grid.addColumn(TeamMember::isActive).setHeader("Active").setFlexGrow(1);

        grid.addComponentColumn(m -> {
            HorizontalLayout btns = new HorizontalLayout();
            btns.setSpacing(true);
            Button editBtn = new Button("Edit", click -> showForm(m));
            Button deleteBtn = new Button("Delete", click -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Delete");
                dialog.add("Remove this team member?");
                dialog.setConfirmText("Delete");
                dialog.setCancelable(true);
                dialog.addConfirmListener(c -> { service.deleteMember(m.getId()); refresh(); Notification.show("Deleted.", 3000, Notification.Position.MIDDLE); });
                dialog.open();
            });
            btns.add(editBtn, deleteBtn);
            return btns;
        }).setHeader("Actions").setFlexGrow(1);

        refresh();
        add(title, addBtn, grid);
    }

    private void showForm(TeamMember member) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(member != null ? "Edit Member" : "Add Member");

        TextField nameField = new TextField("Name *");
        nameField.setRequired(true);
        TextField roleField = new TextField("Role *");
        roleField.setRequired(true);
        TextArea bioField = new TextArea("Bio");
        TextField emailField = new TextField("Email");
        TextField socialField = new TextField("Social Links");

        if (member != null) {
            nameField.setValue(member.getName());
            roleField.setValue(member.getRole());
            bioField.setValue(member.getBio());
            emailField.setValue(member.getEmail());
            socialField.setValue(member.getSocialLinks());
        }

        Button saveBtn = new Button("Save", e -> {
            if (nameField.isEmpty() || roleField.isEmpty()) {
                Notification.show("Name and role are required.", 3000, Notification.Position.MIDDLE);
                return;
            }
            TeamMember m = member != null ? member : new TeamMember();
            m.setName(nameField.getValue());
            m.setRole(roleField.getValue());
            m.setBio(bioField.getValue());
            m.setEmail(emailField.getValue());
            m.setSocialLinks(socialField.getValue());
            m.setActive(true);
            service.saveMember(m);
            dialog.close();
            refresh();
            Notification.show("Saved.", 3000, Notification.Position.MIDDLE);
        });
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancelBtn = new Button("Cancel", e -> dialog.close());

        VerticalLayout form = new VerticalLayout(nameField, roleField, bioField, emailField, socialField,
                new HorizontalLayout(saveBtn, cancelBtn));
        form.setSpacing(true);
        dialog.add(form);
        dialog.open();
    }

    private void refresh() {
        grid.setItems(service.getAllMembers());
    }
}
