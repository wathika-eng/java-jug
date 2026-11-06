# Database Schema

Detailed database schema for Kenya JUG application entities.

---

## Entities & Fields

### Event

Represents a Kenya JUG event (meetup or conference).

```
Event
├── id (Long, PK)
├── title (String, not null) — e.g., "Kenya JUG March Meetup"
├── description (String) — Full event description
├── date (LocalDate, not null) — Event date
├── time (LocalTime) — Event start time
├── location (String, not null) — Physical location/venue
├── type (String, not null) — "MEETUP" or "CONFERENCE"
├── image (String) — URL or path to event image
├── status (String) — "SCHEDULED", "ONGOING", "COMPLETED", "CANCELLED"
├── createdAt (LocalDateTime, not null)
├── updatedAt (LocalDateTime)
└── Relationships:
    ├── Sessions (1:N) → EventSession
    ├── Registrations (1:N) → Registration
```

### EventSession

Represents an individual session/talk within an event.

```
EventSession
├── id (Long, PK)
├── eventId (Long, FK) → Event, not null
├── title (String, not null) — Session title
├── description (String)
├── startTime (LocalTime, not null)
├── endTime (LocalTime, not null)
├── room (String) — Room or track identifier
├── speakerId (Long, FK) → Speaker (nullable)
├── createdAt (LocalDateTime)
└── updatedAt (LocalDateTime)
```

### Speaker

Represents a speaker at Kenya JUG events.

```
Speaker
├── id (Long, PK)
├── name (String, not null)
├── title (String, not null) — Job title
├── bio (String) — Speaker biography
├── imageUrl (String) — URL to speaker photo
├── email (String) — Contact email
├── socialLinks (String) — JSON or serialized links (Twitter, LinkedIn, etc.)
├── createdAt (LocalDateTime)
└── updatedAt (LocalDateTime)
   Relationships:
   └── Sessions (1:N) ← EventSession.speakerId
```

### Registration

Represents an event registration by an attendee.

```
Registration
├── id (Long, PK)
├── eventId (Long, FK) → Event, not null
├── attendeeName (String, not null)
├── attendeeEmail (String, not null) — Must be unique per event
├── registrationDate (LocalDateTime, not null)
├── status (String) — "CONFIRMED", "CANCELLED"
├── createdAt (LocalDateTime)
└── updatedAt (LocalDateTime)

Unique Constraint: (eventId, attendeeEmail)
```

### TeamMember

Represents a Kenya JUG team member or leader.

```
TeamMember
├── id (Long, PK)
├── name (String, not null)
├── role (String, not null) — e.g., "Founder", "Community Lead"
├── bio (String)
├── imageUrl (String) — URL to team member photo
├── email (String)
├── socialLinks (String) — JSON or serialized links
├── isActive (Boolean) — Display active members only
├── createdAt (LocalDateTime)
└── updatedAt (LocalDateTime)
```

### Resource

Represents a community resource (tutorial, tool, documentation).

```
Resource
├── id (Long, PK)
├── title (String, not null)
├── description (String)
├── url (String, not null) — External link (must be unique)
├── resourceType (String, not null) — "TUTORIAL", "TOOL", "DOCUMENTATION", "OTHER"
├── category (String, not null) — e.g., "Spring Boot", "Microservices", "Testing"
├── isActive (Boolean) — Display active resources only
├── createdAt (LocalDateTime)
└── updatedAt (LocalDateTime)

Unique Constraint: url
```

### Page

Represents static content pages (About, Contact info, etc.).

```
Page
├── id (Long, PK)
├── slug (String, not null) — URL-friendly identifier (unique)
├── title (String, not null) — Page title
├── content (String) — HTML or markdown content
├── isPublished (Boolean) — Control page visibility
├── createdAt (LocalDateTime)
└── updatedAt (LocalDateTime)

Unique Constraint: slug
```

---

## Relationships

```
Event (1) ─── (N) EventSession
  └─ Each event can have multiple sessions

Speaker (1) ──── (N) EventSession
  └─ Each speaker can lead multiple sessions

Event (1) ────── (N) Registration
  └─ Each event can have multiple registrations

TeamMember (no direct relationships)
  └─ Standalone entity for team display

Resource (no direct relationships)
  └─ Standalone entity for resource curation

Page (no direct relationships)
  └─ Standalone entity for static content
```

---

## Key Indexes

For optimal query performance:

```sql
-- Event lookups
CREATE INDEX idx_event_date ON Event(date);
CREATE INDEX idx_event_status ON Event(status);

-- EventSession lookups
CREATE INDEX idx_event_session_event_id ON EventSession(eventId);
CREATE INDEX idx_event_session_speaker_id ON EventSession(speakerId);

-- Registration queries
CREATE INDEX idx_registration_event_id ON Registration(eventId);
CREATE INDEX idx_registration_email ON Registration(attendeeEmail);
CREATE INDEX idx_registration_unique ON Registration(eventId, attendeeEmail);

-- Resource lookups
CREATE INDEX idx_resource_category ON Resource(category);
CREATE INDEX idx_resource_type ON Resource(resourceType);

-- Page lookups
CREATE INDEX idx_page_slug ON Page(slug);

-- Team lookups
CREATE INDEX idx_team_member_active ON TeamMember(isActive);
```

---

## Sample Data Structure

### Event with Sessions Example

```
Event: "Kenya JUG February 2025 Meetup"
├── date: 2025-02-15
├── time: 18:00
├── location: Tech Hub Nairobi
├── type: MEETUP
└── Sessions:
    ├── Session 1: "Spring Boot 3 Features" (18:00-18:45)
    │   └── Speaker: John Mwangi
    └── Session 2: "Building Microservices" (18:45-19:30)
        └── Speaker: Jane Kipchoge
```

### Registration Example

```
Registration:
├── eventId: 1 (Kenya JUG February Meetup)
├── attendeeName: "Peter Mutua"
├── attendeeEmail: "peter@example.com"
├── registrationDate: 2025-02-01T14:30:00
└── status: CONFIRMED
```

---

## Notes

- All timestamps are stored as `LocalDateTime` (UTC assumed)
- Soft deletes not used; deletions are hard deletes
- Social links can be stored as JSON or comma-separated URLs
- Images can be stored as URLs (external) or file paths (server-hosted)
- Email uniqueness constraint is per event (same email can register for multiple events)
- Event status helps track event lifecycle and filter display
- Resource and Page entities are managed by admins for public consumption
