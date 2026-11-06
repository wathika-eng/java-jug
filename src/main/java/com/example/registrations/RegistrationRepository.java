package com.example.registrations;

import com.example.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByEventAndAttendeeEmail(Event event, String attendeeEmail);
    List<Registration> findByEventId(Long eventId);
}
