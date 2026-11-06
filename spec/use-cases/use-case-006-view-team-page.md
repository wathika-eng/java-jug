# UC-006: View Team Page

---

**Goal:** As a community member, I want to see the Kenya JUG team members so that I can know who leads the organization and how to contact them.

**Status:** Pending
**Date:** 2025-01-20

---

## Actors

- **Primary actor:** Visitor (anonymous user)

---

## Preconditions

- At least one team member exists in the system

---

## Trigger

User clicks the "Team" link in the navigation or visits the team page.

---

## Main Flow

1. System loads the team page.
2. System displays a title: "Kenya JUG Team" or "Leadership".
3. System displays team member cards in a grid layout.
4. Each team member card shows name, role, and profile image.
5. Each card may include a brief bio or description.
6. Each card displays social media or contact links (e.g., Twitter, LinkedIn, email).
7. User can scroll through or view all team members.

---

## Alternative Flows

### AF-1: No team members

**Branches from:** Main Flow step 2
**Condition:** No team members are configured in the system.

1. System displays a placeholder message: "Team information coming soon."
2. Use case ends.

---

## Postconditions

- **On success:** Team member information is fully displayed with contact/social links accessible.
- **On failure:** Placeholder message displayed.

---

## Business Rules

| ID | Rule |
|----|------|
| BR-01 | Team member cards must display name and role at minimum |
| BR-02 | Profile images should be visible and consistent in size |
| BR-03 | Social media links must open in new tabs/windows |
| BR-04 | Team layout must be responsive and display as single column on mobile |

---

## Tests

- [ ] Team page loads with correct title (Main Flow steps 1–2)
- [ ] Team member cards display in grid layout (Main Flow step 3)
- [ ] Each card shows name, role, and image (Main Flow step 4)
- [ ] Bio or description visible on cards (Main Flow step 5)
- [ ] Social/contact links functional and open in new tabs (Main Flow step 6)
- [ ] Empty state displays when no team members exist (AF-1)
- [ ] Layout responsive on mobile, tablet, desktop

---

## UI Surface

| Page | Access |
|------|--------|
| Team | Anonymous |
