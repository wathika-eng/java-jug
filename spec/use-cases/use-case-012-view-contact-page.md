# UC-012: View Contact Page

---

**Goal:** As a community member, I want to find contact information or send a message to Kenya JUG so that I can get in touch with the organization.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Visitor (anonymous user)

---

## Preconditions

- None (public page)

---

## Trigger

User clicks the "Contact" link in the navigation or visits the contact page.

---

## Main Flow

1. System loads the contact page.
2. System displays a title: "Contact Us" or similar.
3. System displays contact information such as email, phone, or mailing address.
4. System displays social media links for Kenya JUG.
5. System displays a contact form with fields for name, email, subject, and message.
6. User fills in the contact form fields.
7. User clicks the "Send" button.
8. System validates the input (name, email, and message are required; email format is valid).
9. System sends the message (to admin inbox or configured email).
10. System displays a success message: "Thank you for your message. We will get back to you soon."
11. Form is cleared or user is shown a confirmation page.

---

## Alternative Flows

### AF-1: Invalid form input

**Branches from:** Main Flow step 8
**Condition:** Required fields are missing or email format is invalid.

1. System displays validation error messages next to invalid fields.
2. Form remains open with user data preserved.
3. Returns to Main Flow step 7.

### AF-2: Email send failure

**Branches from:** Main Flow step 9
**Condition:** Email service fails or is unavailable.

1. System displays an error message: "Sorry, we couldn't send your message. Please try again later."
2. Form remains open with user data preserved.
3. Returns to Main Flow step 7.

---

## Postconditions

- **On success:** Message is sent; success message displayed; form is cleared.
- **On failure:** Message is not sent; error displayed; form remains open for retry.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Name, email, and message fields are mandatory |
| BR-02 | Email must be in valid format (RFC 5322) |
| BR-03 | Messages must be sent to a configured admin email |
| BR-04 | Subject field is optional but recommended |
| BR-05 | Contact form should include CSRF protection |

---

## Tests

- [ ] Contact page loads with correct title (Main Flow steps 1–2)
- [ ] Contact information displayed (Main Flow steps 3–4)
- [ ] Contact form displays with all required fields (Main Flow step 5)
- [ ] Valid input accepted and message sent (Main Flow steps 7–10)
- [ ] Success message displayed (Main Flow step 10)
- [ ] Invalid email rejected (AF-1)
- [ ] Missing required field rejected (AF-1)
- [ ] Email send failure handled gracefully (AF-2)
- [ ] Form data preserved on validation error

---

## UI Surface

| Page | Access |
|------|--------|
| Contact | Anonymous |
