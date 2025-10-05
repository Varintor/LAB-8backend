package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;
import se331.lab.service.EventService;
import se331.lab.util.LabMapper;

@RestController   // ใช้ RestController แทน Controller (จะได้ return JSON ตรงๆ)
@RequiredArgsConstructor
public class EventController {
    final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false, defaultValue = "10") Integer perPage,
            @RequestParam(value = "_page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description) {

        perPage = (perPage == null || perPage <= 0) ? 10 : perPage;
        page = (page == null || page <= 0) ? 1 : page;

        Page<Event> pageOutput;
        if (title == null || title.isBlank()) {
            pageOutput = eventService.getEvents(page, perPage);
        } else {
            pageOutput = eventService.getEvents(
                    title,
                    PageRequest.of(page - 1, perPage)
            );
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));

        return new ResponseEntity<>(
                LabMapper.INSTANCE.getEventDTOs(pageOutput.getContent()), // ✅ ใช้ Mapper แทน
                responseHeaders,
                HttpStatus.OK
        );
    }


    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getEventDTO(output));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The given id is not found"
            );
        }
    }
    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        // ถ้า frontend ส่ง id = 0 → reset ให้ null
        if (event.getId() != null && event.getId() == 0) {
            event.setId(null);
        }

        Event output = eventService.save(event);
        return ResponseEntity.ok(LabMapper.INSTANCE.getEventDTO(output));
    }

}
