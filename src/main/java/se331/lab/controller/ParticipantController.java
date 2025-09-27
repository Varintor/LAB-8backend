package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.entity.Participant;
import se331.lab.repository.ParticipantRepository;
import se331.lab.util.LabMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    final ParticipantRepository participantRepository;

    @GetMapping("/participants")
    public ResponseEntity<?> getParticipants() {
        return ResponseEntity.ok(
                LabMapper.INSTANCE.getParticipantDTOs(participantRepository.findAll())
        );
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<?> getParticipant(@PathVariable Long id) {
        return participantRepository.findById(id)
                .map(p -> ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }
}

