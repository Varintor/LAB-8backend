package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;

@RestController
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;

    @GetMapping("organizers")
    public ResponseEntity<?> getOrganizers(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {

        Page<Organizer> result = organizerService.getOrganizers(perPage, page);

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(result.getTotalElements()))
                .body(result.getContent());
    }


    // ดึง organizer ตาม id
    @GetMapping("organizers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer organizer = organizerService.getOrganizer(id);
        if (organizer != null) {
            return ResponseEntity.ok(organizer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/organizers")
    public Organizer addOrganizer(@RequestBody Organizer organizer) {
        organizer.setId(null); // กัน frontend ส่ง id=0 มาด้วย
        return organizerService.save(organizer);
    }
}

