# UC-010: Admin Manage Speakers

---

**Goal:** As an admin, I want to create and manage speaker profiles so that I can assign speakers to event sessions and display speaker information on event details pages.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Administrator (authenticated, with admin role)

---

## Preconditions

- User is logged in with admin role
- User has navigated to the admin speakers management page

---

## Trigger

Admin clicks "Manage Speakers" or accesses the admin speakers page.

---

## Main Flow

1. System loads the admin speakers management page.
2. System displays a list of all speakers with name, title, and status.
3. System displays an "Add Speaker" button.
4. Admin clicks "Add Speaker" to open a form.
5. Admin fills in speaker details (name, title, bio, image, email, social links).
6. Admin submits the form.
7. System validates the input (name and title are required).
8. System creates the speaker record and adds it to the list.
9. System displays a success message.
10. Admin can click "Edit" on any speaker to modify their profile.
11. Admin can click "Delete" to remove a speaker (with confirmation).
12. Changes are persisted to the database.

---

## Alternative Flows

### AF-1: Invalid form input

**Branches from:** Main Flow step 7
**Condition:** Required fields are missing or invalid.

1. System displays validation error messages next to invalid fields.
2. Form remains open for user to correct the input.
3. Returns to Main Flow step 6.

### AF-2: Delete speaker with confirmation

**Branches from:** Main Flow step 11
**Condition:** Admin clicks Delete.

1. System displays a confirmation dialog: "Are you sure you want to delete this speaker?"
2. If admin confirms, speaker is deleted.
3. If the speaker is assigned to any sessions, system displays a warning and offers to unassign sessions.
4. If admin confirms deletion, speaker is removed and sessions are updated.
5. If admin cancels, use case returns to Main Flow step 2.

### AF-3: Speaker already assigned to sessions

**Branches from:** Main Flow step 11
**Condition:** Speaker is assigned to one or more event sessions.

1. System displays a warning: "This speaker is assigned to [X] session(s). Deleting will unassign these sessions."
2. Admin can choose to proceed with deletion or cancel.
3. Returns to Main Flow step 2.

---

## Postconditions

- **On success:** Speaker is created, updated, or deleted; list reflects changes; success message displayed.
- **On failure:** Speaker is not persisted; error messages displayed; form remains open.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Name and title are mandatory fields |
| BR-02 | Bio and image are optional but recommended |
| BR-03 | Email must be in valid format if provided |
| BR-04 | Social media links must be valid URLs if provided |
| BR-05 | Deleting a speaker unassigns it from all sessions |

---

## Tests

- [ ] Admin speakers page loads with list of speakers (Main Flow steps 1–2)
- [ ] Add Speaker form displays and accepts valid input (Main Flow steps 3–5)
- [ ] Speaker created and added to list (Main Flow steps 8–9)
- [ ] Edit functionality allows modifying speaker details (Main Flow step 10)
- [ ] Delete with confirmation removes speaker (Main Flow step 11, AF-2)
- [ ] Validation error displayed for missing required fields (AF-1)
- [ ] Warning displayed if speaker is assigned to sessions (AF-3)
- [ ] Unassignment handled correctly when deleting (AF-2)
- [ ] Changes persisted to database

---

## UI Surface

| Page | Access |
|------|--------|
| Admin Speakers List | Admin |
| Add/Edit Speaker Form | Admin |
