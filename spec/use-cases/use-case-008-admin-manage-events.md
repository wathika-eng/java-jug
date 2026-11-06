# UC-008: Admin Manage Events

---

**Goal:** As an admin, I want to create, edit, and delete events so that I can manage Kenya JUG's event information.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Administrator (authenticated, with admin role)

---

## Preconditions

- User is logged in with admin role
- User has navigated to the admin events management page

---

## Trigger

Admin clicks "Manage Events" or accesses the admin events page.

---

## Main Flow

1. System loads the admin events management page.
2. System displays a list of all events with title, date, type, and status.
3. System displays a "Create Event" button.
4. Admin can click "Create Event" to open a form to create a new event.
5. Admin fills in event details (title, description, date, time, location, type, image).
6. Admin submits the form.
7. System validates the input (title, date, and location are required).
8. System creates the event and adds it to the list.
9. System displays a success message.
10. Admin can click "Edit" on any event to modify its details.
11. Admin can click "Delete" to remove an event (with confirmation).
12. Changes are persisted to the database.

---

## Alternative Flows

### AF-1: Invalid form input

**Branches from:** Main Flow step 7
**Condition:** Required fields are missing or invalid (e.g., date is in the past).

1. System displays validation error messages next to invalid fields.
2. Form remains open for user to correct the input.
3. Returns to Main Flow step 6.

### AF-2: Delete event with confirmations

**Branches from:** Main Flow step 11
**Condition:** Admin clicks Delete.

1. System displays a confirmation dialog: "Are you sure you want to delete this event?"
2. If admin confirms, event is deleted and list is updated.
3. If admin cancels, use case returns to Main Flow step 2.

### AF-3: Duplicate event title

**Branches from:** Main Flow step 8
**Condition:** Event with the same title already exists.

1. System displays a warning: "An event with this title already exists."
2. Admin can choose to save with the same title or modify it.
3. Returns to Main Flow step 6.

---

## Postconditions

- **On success:** Event is created, updated, or deleted; list reflects changes; success message displayed.
- **On failure:** Event is not persisted; error messages displayed; form remains open.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Title, date, and location are mandatory fields |
| BR-02 | Event date must be in the future or today |
| BR-03 | Event type must be selected from predefined options (meetup/conference) |
| BR-04 | Admin can edit or delete any event |
| BR-05 | Deleted events cannot be recovered (hard delete) |

---

## Tests

- [ ] Admin events page loads with list of events (Main Flow steps 1–2)
- [ ] Create Event form displays and accepts valid input (Main Flow steps 3–5)
- [ ] Event created and added to list (Main Flow steps 8–9)
- [ ] Edit functionality allows modifying event details (Main Flow step 10)
- [ ] Delete with confirmation removes event (Main Flow step 11, AF-2)
- [ ] Validation error displayed for missing fields (AF-1)
- [ ] Warning shown for duplicate title (AF-3)
- [ ] Changes persisted to database

---

## UI Surface

| Page | Access |
|------|--------|
| Admin Events List | Admin |
| Create/Edit Event Form | Admin |
