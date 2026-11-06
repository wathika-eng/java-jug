# UC-002: View Events List

---

**Goal:** As a community member, I want to view all Kenya JUG events so that I can find events that interest me and register.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Visitor (anonymous user)

---

## Preconditions

- At least one event exists in the system

---

## Trigger

User clicks the "Events" navigation link or visits the events page.

---

## Main Flow

1. System loads the events page.
2. System displays a list or grid of all events.
3. System shows event cards with title, date, time, location, and a thumbnail image.
4. System displays event type (meetup or conference) as a badge or label.
5. System shows a "Learn More" or "Register" button on each event card.
6. User can scroll through the event list.
7. User can click on an event card to view event details.

---

## Alternative Flows

### AF-1: No events available

**Branches from:** Main Flow step 2
**Condition:** No events exist in the system.

1. System displays an empty state message: "No events scheduled at this time. Check back soon!"
2. Use case ends.

### AF-2: Filter or search events

**Branches from:** Main Flow step 2
**Condition:** User interacts with filter/search controls (if present).

1. System provides search or filter options (by date, type, etc.).
2. User selects filter criteria.
3. System displays filtered event list.
4. Returns to Main Flow step 3.

---

## Postconditions

- **On success:** Events list is displayed; user can view event details or register.
- **On failure:** Empty state message displayed.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Events must be sorted by date (upcoming first) |
| BR-02 | Each event card must display title, date, time, and location |
| BR-03 | Event type (meetup/conference) must be clearly visible |
| BR-04 | Cancelled or past events may be archived or shown separately |

---

## Tests

- [ ] Events list displays with all events (Main Flow step 2–3)
- [ ] Event cards show correct information (date, time, location) (Main Flow step 3)
- [ ] Event type badge is visible (Main Flow step 4)
- [ ] Clicking event card navigates to event details (Main Flow step 7)
- [ ] Empty state displays when no events exist (AF-1)
- [ ] Events sorted by date in ascending order (BR-01)

---

## UI Surface

| Page | Access |
|------|--------|
| Events List | Anonymous |
