# Data Model

> Entity definitions and relationships. Evolves as features are added.

| Entity | Key Fields | Relationships |
|--------|-----------|------------------|
| Event | id, title, description, date, time, location, type (meetup/conference), image, status | Has many EventSessions, EventSpeakers, Registrations |
| EventSession | id, title, description, startTime, endTime, speakerId, room | Belongs to Event, Speaker |
| Speaker | id, name, title, bio, imageUrl, email, socialLinks | Has many EventSessions |
| Registration | id, eventId, attendeeEmail, attendeeName, registrationDate, status | Belongs to Event |
| Page | id, slug, title, content, isPublished | Static pages (about, contact, etc.) |
| TeamMember | id, name, role, bio, imageUrl, email, socialLinks | Team information display |
| Resource | id, title, description, url, resourceType (documentation/tutorial/tool), category | Community resources and links |
