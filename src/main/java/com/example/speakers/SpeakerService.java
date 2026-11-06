package com.example.speakers;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpeakerService {

    private final SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> getAllSpeakers() {
        return speakerRepository.findAll();
    }

    public Optional<Speaker> getSpeakerById(Long id) {
        return speakerRepository.findById(id);
    }

    public Speaker saveSpeaker(Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    public void deleteSpeaker(Long id) {
        speakerRepository.deleteById(id);
    }
}
