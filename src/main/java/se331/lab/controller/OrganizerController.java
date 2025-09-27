package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.service.OrganizerService;
import se331.lab.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class OrganizerController {
    private final OrganizerService organizerService;

    @GetMapping("/organizers")
    public ResponseEntity<?> getOrganizers() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(organizerService.getAllOrganizer()) );
    }
}

//    public ResponseEntity<?> getOrganizers(
//            @RequestParam(value = "_limit", required = false, defaultValue = "10") Integer perPage,
//            @RequestParam(value = "_page", required = false, defaultValue = "1") Integer page) {
//
//        Page<Organizer> result = organizerService.getOrganizers(perPage, page);
//
//        return ResponseEntity.ok()
//                .header("X-Total-Count", String.valueOf(result.getTotalElements()))
//                .body(result.getContent());
//    }
//
//    // ดึง organizer ตาม id
//    @GetMapping("/organizers/{id}")
//    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
//        Organizer organizer = organizerService.getOrganizer(id);
//        if (organizer != null) {
//            return ResponseEntity.ok(organizer);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @PostMapping("/organizers")
//    public Organizer addOrganizer(@RequestBody Organizer organizer) {
//        organizer.setId(null); // กัน frontend ส่ง id=0 มาด้วย
//        return organizerService.save(organizer);
//    }


