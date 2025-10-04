package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;
import se331.lab.util.LabMapper;
@RestController
@RequestMapping("/organizers")
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;

    @GetMapping
    public ResponseEntity<?> getOrganizers(
            @RequestParam(value = "_limit", required = false, defaultValue = "10") Integer perPage,
            @RequestParam(value = "_page", required = false, defaultValue = "1") Integer page) {

        Page<Organizer> result = organizerService.getOrganizers(page, perPage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(result.getTotalElements()));

        return new ResponseEntity<>(
                LabMapper.INSTANCE.getOrganizerDTO(result.getContent()),
                headers,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer) {
        if (organizer.getId() != null && organizer.getId() == 0) {
            organizer.setId(null); // reset id
        }
        Organizer output = organizerService.save(organizer);
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(output));
    }
}
