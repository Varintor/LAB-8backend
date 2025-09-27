package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.entity.Organizer;
import se331.lab.entity.OrganizerDTO;
import se331.lab.repository.OrganizerRepository;
import se331.lab.service.OrganizerService;
import se331.lab.util.LabMapper;

import java.util.List;

@RestController
@RequestMapping("/organizers")
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerRepository organizerRepository;

    @GetMapping
    public List<OrganizerDTO> getAllOrganizers() {
        return LabMapper.INSTANCE.getOrganizerDTO(organizerRepository.findAll());
    }

    // ✅ เพิ่มตรงนี้สำหรับ /organizers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<OrganizerDTO> getOrganizerById(@PathVariable Long id) {
        return organizerRepository.findById(id)
                .map(org -> ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(org)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<OrganizerDTO> addOrganizer(@RequestBody OrganizerDTO organizerDTO) {
        Organizer organizer = LabMapper.INSTANCE.getOrganizer(organizerDTO);
        Organizer saved = organizerRepository.save(organizer);
        organizer.setId(null);
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(saved));
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
//   }
//    @PostMapping("/organizers")
//    public Organizer addOrganizer(@RequestBody Organizer organizer) {
//        organizer.setId(null); // กัน frontend ส่ง id=0 มาด้วย
//        return organizerService.save(organizer);
//    }


