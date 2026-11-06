# UC-011: Admin Manage Resources

---

**Goal:** As an admin, I want to create, edit, and delete community resources so that I can curate a list of tutorials, tools, and documentation for Kenya JUG members.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Administrator (authenticated, with admin role)

---

## Preconditions

- User is logged in with admin role
- User has navigated to the admin resources management page

---

## Trigger

Admin clicks "Manage Resources" or accesses the admin resources page.

---

## Main Flow

1. System loads the admin resources management page.
2. System displays a list of all resources with title, category, and resource type.
3. System displays an "Add Resource" button.
4. Admin clicks "Add Resource" to open a form.
5. Admin fills in resource details (title, description, URL, category, resource type).
6. Admin submits the form.
7. System validates the input (title, URL, and category are required).
8. System validates that the URL is in a valid format.
9. System creates the resource record and adds it to the list.
10. System displays a success message.
11. Admin can click "Edit" on any resource to modify its details.
12. Admin can click "Delete" to remove a resource (with confirmation).
13. Changes are persisted to the database.

---

## Alternative Flows

### AF-1: Invalid form input

**Branches from:** Main Flow step 7
**Condition:** Required fields are missing or invalid (e.g., invalid URL format).

1. System displays validation error messages next to invalid fields.
2. Form remains open for user to correct the input.
3. Returns to Main Flow step 6.

### AF-2: Delete resource with confirmation

**Branches from:** Main Flow step 12
**Condition:** Admin clicks Delete.

1. System displays a confirmation dialog: "Are you sure you want to delete this resource?"
2. If admin confirms, resource is deleted and list is updated.
3. If admin cancels, use case returns to Main Flow step 2.

### AF-3: Duplicate resource URL

**Branches from:** Main Flow step 9
**Condition:** A resource with the same URL already exists.

1. System displays a warning: "A resource with this URL already exists."
2. Admin can choose to save as a duplicate or modify the URL.
3. Returns to Main Flow step 6.

---

## Postconditions

- **On success:** Resource is created, updated, or deleted; list reflects changes; success message displayed.
- **On failure:** Resource is not persisted; error messages displayed; form remains open.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Title, URL, and category are mandatory fields |
| BR-02 | URL must be a valid and reachable link |
| BR-03 | Resource type must be selected from predefined options (tutorial/tool/documentation/other) |
| BR-04 | Category helps organize resources for browsing |
| BR-05 | Admin can edit or delete any resource |

---

## Tests

- [ ] Admin resources page loads with list of resources (Main Flow steps 1–2)
- [ ] Add Resource form displays and accepts valid input (Main Flow steps 3–5)
- [ ] URL validation works correctly (Main Flow step 8)
- [ ] Resource created and added to list (Main Flow steps 9–10)
- [ ] Edit functionality allows modifying resource details (Main Flow step 11)
- [ ] Delete with confirmation removes resource (Main Flow step 12, AF-2)
- [ ] Validation error displayed for missing required fields (AF-1)
- [ ] Invalid URL rejected (AF-1)
- [ ] Warning shown for duplicate URL (AF-3)
- [ ] Changes persisted to database

---

## UI Surface

| Page | Access |
|------|--------|
| Admin Resources List | Admin |
| Add/Edit Resource Form | Admin |
