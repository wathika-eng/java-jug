package com.example.registrations;

import com.example.events.Event;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Optional<Registration> findByEventAndEmail(Event event, String email) {
        return registrationRepository.findByEventAndAttendeeEmail(event, email);
    }

    public List<Registration> findByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    public Registration register(Event event, String name, String email) {
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setAttendeeName(name);
        registration.setAttendeeEmail(email);
        registration.setRegistrationDate(LocalDateTime.now());
        registration.setStatus("CONFIRMED");
        return registrationRepository.save(registration);
    }
}
