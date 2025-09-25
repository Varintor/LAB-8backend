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
        // ตรวจสอบค่าดีฟอลต์อีกครั้งเพื่อความชัวร์
        perPage = (perPage == null || perPage <= 0) ? 10 : perPage;
        page = (page == null || page <= 0) ? 1 : page;


        Page<Event> pageOutput = eventService.getEventsAnd(
                title, description,
                PageRequest.of(page - 1, perPage)
        );

        if (title == null || title.isBlank()) {
            // ถ้าไม่ได้ส่ง title → ดึงทั้งหมด
            pageOutput = eventService.getEvents(page, perPage);
        } else {
            // ถ้าส่ง title → ดึงเฉพาะที่ match
            pageOutput = eventService.getEvents(
                    title,
                    PageRequest.of(page - 1, perPage)
            );
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));

        return new ResponseEntity<>(
                pageOutput.getContent(),
                responseHeaders,
                HttpStatus.OK
        );
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The given id is not found"
            );
        }
    }
    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        // ✅ reset id ถ้า frontend ส่งมาเป็น 0
        if (event.getId() != null && event.getId() == 0) {
            event.setId(null);
        }

        Event output = eventService.save(event);
        return ResponseEntity.ok(output);
    }

}
