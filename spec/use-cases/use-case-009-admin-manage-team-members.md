# UC-009: Admin Manage Team Members

---

**Goal:** As an admin, I want to create, edit, and delete team member profiles so that I can manage the Kenya JUG team information displayed on the website.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Administrator (authenticated, with admin role)

---

## Preconditions

- User is logged in with admin role
- User has navigated to the admin team management page

---

## Trigger

Admin clicks "Manage Team" or accesses the admin team page.

---

## Main Flow

1. System loads the admin team management page.
2. System displays a list of all team members with name, role, and profile image.
3. System displays a "Add Team Member" button.
4. Admin clicks "Add Team Member" to open a form.
5. Admin fills in team member details (name, role, bio, profile image, email, social links).
6. Admin submits the form.
7. System validates the input (name and role are required).
8. System creates the team member record and adds it to the list.
9. System displays a success message.
10. Admin can click "Edit" on any team member to modify their profile.
11. Admin can click "Delete" to remove a team member (with confirmation).
12. Changes are persisted to the database.

---

## Alternative Flows

### AF-1: Invalid form input

**Branches from:** Main Flow step 7
**Condition:** Required fields are missing or invalid (e.g., invalid email format).

1. System displays validation error messages next to invalid fields.
2. Form remains open for user to correct the input.
3. Returns to Main Flow step 6.

### AF-2: Delete team member with confirmation

**Branches from:** Main Flow step 11
**Condition:** Admin clicks Delete.

1. System displays a confirmation dialog: "Are you sure you want to remove this team member?"
2. If admin confirms, team member is deleted and list is updated.
3. If admin cancels, use case returns to Main Flow step 2.

### AF-3: Image upload handling

**Branches from:** Main Flow step 5
**Condition:** Admin uploads a profile image.

1. System accepts common image formats (JPEG, PNG, GIF).
2. Image is stored and associated with the team member.
3. Image is resized/optimized for web display.
4. Main flow continues.

---

## Postconditions

- **On success:** Team member is created, updated, or deleted; list reflects changes; success message displayed.
- **On failure:** Team member is not persisted; error messages displayed; form remains open.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Name and role are mandatory fields |
| BR-02 | Email field must be in valid format if provided |
| BR-03 | Social media links must be valid URLs |
| BR-04 | Profile image is optional but recommended |
| BR-05 | Admin can edit or delete any team member |

---

## Tests

- [ ] Admin team page loads with list of members (Main Flow steps 1–2)
- [ ] Add Team Member form displays and accepts valid input (Main Flow steps 3–5)
- [ ] Team member created and added to list (Main Flow steps 8–9)
- [ ] Edit functionality allows modifying team member details (Main Flow step 10)
- [ ] Delete with confirmation removes team member (Main Flow step 11, AF-2)
- [ ] Validation error displayed for missing required fields (AF-1)
- [ ] Invalid email rejected; valid email accepted (AF-1)
- [ ] Image upload handled correctly (AF-3)
- [ ] Social media links validated (BR-03)
- [ ] Changes persisted to database

---

## UI Surface

| Page | Access |
|------|--------|
| Admin Team List | Admin |
| Add/Edit Team Member Form | Admin |
