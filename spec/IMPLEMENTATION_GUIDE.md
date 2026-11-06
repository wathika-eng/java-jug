# Implementation Guide — Kenya JUG Website

This document provides guidance for implementing the Kenya JUG website specification.

---

## Overview

The Kenya JUG website is a Vaadin-based web application that replicates the UI/UX of https://kenyajug.org/?v=2. The application provides:

- **Public Pages**: Homepage, events list/details, about, team, resources, contact
- **Event Registration**: Users can register for events with email confirmation
- **Admin Backend**: Authenticated admin area to manage events, speakers, team members, and resources

---

## Project Structure

```
src/
├── main/
│   ├── java/com/example/specdriven/
│   │   ├── Application.java                 # Spring Boot entry point
│   │   ├── events/                          # Event management
│   │   ├── speakers/                        # Speaker management
│   │   ├── team/                            # Team management
│   │   ├── resources/                       # Resources management
│   │   ├── registrations/                   # Event registrations
│   │   └── admin/                           # Admin UI views
│   ├── frontend/views/                      # Hilla React public views
│   │   ├── home/                            # Homepage
│   │   ├── events/                          # Events list
│   │   ├── event-details/                   # Event details
│   │   ├── about/                           # About page
│   │   ├── team/                            # Team page
│   │   ├── resources/                       # Resources page
│   │   └── contact/                         # Contact page
│   └── resources/
│       └── META-INF/resources/
│           └── styles.css                   # Global styles (Aura theme)
└── test/
    └── java/use-case-tests/                 # Tests organized by use case
```

---

## Implementation Phases

### Phase 1: Setup & Data Model (UC-001, Data Model)

1. **Create entities**: Event, EventSession, Speaker, Registration, TeamMember, Resource, Page
2. **Create repositories** for each entity using Spring Data
3. **Create services** to handle business logic
4. **Set up database** (H2 for development, PostgreSQL for production)
5. **Create initial data** for demo/testing

### Phase 2: Public Pages (UC-002 to UC-007, UC-012)

1. **Homepage (UC-001)**
   - Hero section with Kenya JUG branding
   - Featured events section
   - Quick links to other sections
   - Footer with social links

2. **Events List (UC-002)**
   - Display all events in grid or list
   - Event cards with image, title, date, location, type

3. **Event Details (UC-003)**
   - Full event information, sessions, speakers
   - Speaker bios and contact info
   - Registration button

4. **Event Registration (UC-004)**
   - Simple form: name, email
   - Email validation and confirmation email
   - Success/error handling

5. **About Page (UC-005)**
   - Mission, vision, values, history
   - Key achievements and statistics

6. **Team Page (UC-006)**
   - Team member cards in grid
   - Name, role, bio, photo
   - Social media links

7. **Resources Page (UC-007)**
   - Organized by category (tutorials, tools, documentation)
   - External links with descriptions
   - Category filtering (optional)

8. **Contact Page (UC-012)**
   - Contact information
   - Contact form (name, email, subject, message)
   - Email notification to admin

### Phase 3: Admin Backend (UC-008 to UC-011)

1. **Event Management (UC-008)**
   - List all events
   - Create/edit/delete events
   - Manage event sessions
   - Add speakers to sessions

2. **Team Management (UC-009)**
   - List team members
   - Create/edit/delete team members
   - Upload profile images

3. **Speaker Management (UC-010)**
   - List speakers
   - Create/edit/delete speakers
   - Manage speaker assignments to sessions

4. **Resource Management (UC-011)**
   - List resources
   - Create/edit/delete resources
   - Organize by category

### Phase 4: Polish & Testing

1. Responsive design testing (mobile, tablet, desktop)
2. Accessibility testing
3. Email notification testing
4. Admin workflow optimization
5. Performance optimization

---

## Technology Stack

- **Frontend Framework**: Hilla (React) for public views, Vaadin Flow for admin
- **Backend Framework**: Spring Boot with Spring Data
- **Java Version**: 17+ (check pom.xml)
- **Database**: H2 (dev), PostgreSQL (production)
- **UI Components**: Vaadin components (Button, Grid, Form, etc.)
- **Styling**: Aura theme with `--aura-*` CSS variables
- **Build Tool**: Maven
- **Testing**: JUnit 5, Browserless Tests, Vitest

---

## Key Business Rules

### Events (UC-002, UC-003, UC-008)
- BR-01: Events sorted by date (upcoming first)
- BR-02: Event type must be meetup or conference
- BR-03: Event must have title, date, time, location
- BR-04: Past events may be archived

### Registrations (UC-004)
- BR-01: Name and email required
- BR-02: Email format validated
- BR-03: One registration per email per event
- BR-04: Confirmation email sent within seconds
- BR-05: Registration closes at event date/time

### Team (UC-006, UC-009)
- BR-01: Team member must have name and role
- BR-02: Profile images should be consistent size
- BR-03: Social links open in new tabs
- BR-04: Layout responsive on mobile

### Speakers (UC-010)
- BR-01: Speaker must have name and title
- BR-02: Can be assigned to multiple sessions
- BR-03: Deleting speaker unassigns all sessions

### Resources (UC-007, UC-011)
- BR-01: Resource must have title, URL, category
- BR-02: URL must be valid and reachable
- BR-03: Resources organized by category
- BR-04: Links open in new tabs

---

## Design Considerations

### Color Scheme
Use Kenya JUG's actual colors if available from the website. Otherwise:
- Primary: Blue (`--aura-accent-color-light`)
- Secondary: Complementary color
- Neutrals: Gray scale for text and borders

### Typography
- Font: Instrument Sans (Aura default)
- Headings: `--aura-font-size-l` to `-xl` with `--aura-font-weight-semibold`
- Body: `--aura-font-size-m` with `--aura-font-weight-regular`

### Layout
- **Mobile** (< 640px): Single column, stacked navigation
- **Tablet** (640–1024px): Two-column, side-by-side content
- **Desktop** (> 1024px): Multi-column layouts

### Images
- Use responsive images with CSS `max-width: 100%`
- Optimize for web (JPEG for photos, PNG for graphics)
- Provide alt text for accessibility

---

## Testing Strategy

### Unit Tests
- Test entity validations
- Test service business logic
- Test repository queries

### Integration Tests
- Test API endpoints
- Test database operations
- Test email sending

### UI Tests
- Test form validation (UC-004, UC-012)
- Test navigation between pages
- Test responsive design

### E2E Tests
- Complete user flows (UC-001 → UC-004)
- Admin workflows (UC-008 → UC-011)

---

## Deployment Checklist

- [ ] All use cases implemented and tested
- [ ] Responsive design verified on mobile/tablet/desktop
- [ ] Admin authentication configured
- [ ] Email service configured (SMTP)
- [ ] Database migrations created
- [ ] Security headers configured
- [ ] Error handling implemented
- [ ] Performance optimized (caching, lazy loading)
- [ ] Documentation updated
- [ ] User acceptance testing completed

---

## Next Steps

1. **Read the full specifications**: Start with `project-context.md`, then each use case
2. **Review existing code**: Check pom.xml for dependencies and versions
3. **Begin Phase 1**: Set up entities and database
4. **Implement incrementally**: Complete one use case at a time
5. **Test thoroughly**: Write tests as you implement features
6. **Iterate**: Refine based on testing and feedback

---

## Questions & Clarifications

When implementing, if you encounter ambiguities or missing information:

1. **Check the relevant use case file** for detailed flows and business rules
2. **Refer to the data model** for entity relationships
3. **Review the design system** for styling guidelines
4. **Check architecture.md** for technical decisions

If specifications need updates, update the relevant spec file and keep it as the source of truth.
