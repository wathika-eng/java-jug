# UC-004: Register for Event

---

**Goal:** As a community member, I want to register for a Kenya JUG event so that I can attend and receive event information.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Visitor (anonymous user)

---

## Preconditions

- Event exists and is open for registration
- Event registration deadline has not passed
- User is viewing the event details page

---

## Trigger

User clicks the "Register" button on the event details page.

---

## Main Flow

1. System displays a registration form with fields for attendee name and email.
2. User enters their name and email address.
3. User submits the form.
4. System validates the input (name and email are required; email format is valid).
5. System creates a registration record with the attendee information.
6. System displays a success message: "Registration confirmed! You will receive event details at your email."
7. System sends a confirmation email to the attendee with event information.
8. User is returned to the event details page or a confirmation page.

---

## Alternative Flows

### AF-1: Invalid email format

**Branches from:** Main Flow step 4
**Condition:** Email field is empty or does not match email format.

1. System displays an error message: "Please enter a valid email address."
2. Form remains open for user to correct the input.
3. Returns to Main Flow step 3.

### AF-2: Required field is empty

**Branches from:** Main Flow step 4
**Condition:** Name or email field is empty.

1. System displays an error message: "Please fill in all required fields."
2. Form remains open with previously entered data preserved.
3. Returns to Main Flow step 3.

### AF-3: Duplicate registration

**Branches from:** Main Flow step 5
**Condition:** The same email is already registered for this event.

1. System displays a message: "This email is already registered for this event."
2. System offers an option to update the registration or cancel.
3. If user confirms, use case ends. If user updates, returns to Main Flow step 3.

### AF-4: Registration deadline passed

**Branches from:** Main Flow step 1
**Condition:** Event registration window has closed.

1. System displays a message: "Registration for this event is closed."
2. System displays the event details page without the registration form.
3. Use case ends.

---

## Postconditions

- **On success:** Registration is created and stored; confirmation email sent; user receives success message.
- **On failure:** Registration is not created; error message displayed; form remains open for correction.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Name and email fields are mandatory |
| BR-02 | Email must be in valid format (RFC 5322) |
| BR-03 | Only one registration per email per event is allowed |
| BR-04 | Confirmation email must be sent within seconds of successful registration |
| BR-05 | Registration closes at the event date/time or earlier if specified |

---

## Tests

- [ ] Registration form displays with name and email fields (Main Flow step 1)
- [ ] Valid name and email accepted (Main Flow steps 2–5)
- [ ] Success message displayed and confirmation email sent (Main Flow steps 6–7)
- [ ] Invalid email format rejected (AF-1)
- [ ] Empty required field rejected (AF-2)
- [ ] Duplicate registration detected and handled (AF-3)
- [ ] Registration closed after deadline (AF-4)
- [ ] Confirmation email contains event details

---

## UI Surface

| Page | Access |
|------|--------|
| Registration Form (modal or dedicated page) | Anonymous |
