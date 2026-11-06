# Use Cases Summary

Kenya JUG Website Application — Complete list of features and use cases.

---

## Public Features (Anonymous Access)

| Use Case | File | Description |
|----------|------|-------------|
| UC-001 | `use-case-001-view-homepage.md` | View the homepage with hero section, events, and navigation |
| UC-002 | `use-case-002-view-events-list.md` | View all Kenya JUG events in a list or grid |
| UC-003 | `use-case-003-view-event-details.md` | View detailed information about a specific event |
| UC-004 | `use-case-004-register-for-event.md` | Register for an event with name and email |
| UC-005 | `use-case-005-view-about-page.md` | View organizational mission, vision, and history |
| UC-006 | `use-case-006-view-team-page.md` | View Kenya JUG team members and leadership |
| UC-007 | `use-case-007-view-resources-page.md` | Browse community resources, tutorials, and tools |
| UC-012 | `use-case-012-view-contact-page.md` | View contact information and send messages |

---

## Admin Features (Authenticated, Admin Role)

| Use Case | File | Description |
|----------|------|-------------|
| UC-008 | `use-case-008-admin-manage-events.md` | Create, edit, delete events |
| UC-009 | `use-case-009-admin-manage-team-members.md` | Manage team member profiles and information |
| UC-010 | `use-case-010-admin-manage-speakers.md` | Manage speaker profiles for event sessions |
| UC-011 | `use-case-011-admin-manage-resources.md` | Curate and manage community resources |

---

## Key Entities

- **Event** — Meetups and conferences with dates, locations, and sessions
- **Speaker** — Presenters at events with bios and contact information
- **EventSession** — Individual talks or sessions within an event
- **Registration** — Attendee sign-ups for events
- **TeamMember** — Kenya JUG leadership and core team
- **Resource** — Curated links to tutorials, tools, documentation
- **Page** — Static content pages (About, Contact, etc.)

---

## Navigation Structure

```
Homepage (UC-001)
├── Events (UC-002)
│   └── Event Details (UC-003)
│       └── Register (UC-004)
├── About (UC-005)
├── Team (UC-006)
├── Resources (UC-007)
├── Contact (UC-012)
└── Admin (UC-008 to UC-011)
```

---

## Quick Start for Implementation

1. **Start with UC-001**: Implement the homepage and navigation structure
2. **Add UC-002 & UC-003**: Build the events display and details pages
3. **Add UC-004**: Implement event registration with email validation
4. **Add UC-005 to UC-007**: Build static content pages
5. **Add UC-012**: Add contact form with email notification
6. **Add UC-008 to UC-011**: Build admin backend for content management

---

## Design System Reference

- **Theme**: Vaadin Aura (not Lumo)
- **Base Colors**: Use `--aura-*` CSS variables
- **Typography**: Instrument Sans, use `--aura-font-size-*` tokens
- **Spacing**: Use `--vaadin-gap-*` and `--vaadin-padding-*`
- **Responsive**: Mobile (< 640px), Tablet (640–1024px), Desktop (> 1024px)

See `design-system.md` for complete details.

---

## Architecture Reference

- **Frontend**: Vaadin Flow (Java) for admin, Hilla React for public views
- **Backend**: Spring Boot, Spring Data, Spring Security
- **Database**: Configured in `pom.xml`
- **Routing**: `@Route` for Vaadin Flow, file-based for React
- **Security**: `VaadinSecurityConfigurer` with login form at `/login`

See `architecture.md` for complete details.
