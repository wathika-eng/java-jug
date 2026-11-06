# UC-007: View Resources Page

---

**Goal:** As a developer, I want to access Kenya JUG resources and curated links so that I can find tutorials, documentation, and tools to improve my Java skills.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Visitor (anonymous user)

---

## Preconditions

- At least one resource exists in the system

---

## Trigger

User clicks the "Resources" link in the navigation or visits the resources page.

---

## Main Flow

1. System loads the resources page.
2. System displays a title: "Resources" or "Community Resources".
3. System displays resources organized by category (e.g., tutorials, tools, documentation).
4. Each resource is displayed as a card or list item with title, description, and category.
5. Each resource includes a link to the external resource.
6. System optionally displays resource type badge (e.g., "Tutorial", "Tool", "Documentation").
7. User can click a resource link to open it in a new tab/window.

---

## Alternative Flows

### AF-1: No resources available

**Branches from:** Main Flow step 2
**Condition:** No resources are configured in the system.

1. System displays a message: "Resources coming soon. Stay tuned!"
2. Use case ends.

---

## Postconditions

- **On success:** Resources page is displayed with clickable links to external resources.
- **On failure:** Placeholder message displayed.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Each resource must have a title, description, and external URL |
| BR-02 | Resources should be organized by category for easy browsing |
| BR-03 | Resource links must open in new tabs/windows (target="_blank") |
| BR-04 | Resource type or category badge should be visually distinct |

---

## Tests

- [ ] Resources page loads with correct title (Main Flow steps 1–2)
- [ ] Resources organized by category (Main Flow step 3)
- [ ] Each resource displays title, description, and category (Main Flow step 4)
- [ ] Resource links are clickable and open in new tabs (Main Flow step 7)
- [ ] Resource type badges visible (Main Flow step 6)
- [ ] Empty state displays when no resources exist (AF-1)
- [ ] Page is responsive on all devices

---

## UI Surface

| Page | Access |
|------|--------|
| Resources | Anonymous |
