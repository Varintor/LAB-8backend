package se331.lab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Event;


public interface EventService {
    Integer getEventSize();
    Page<Event> getEvents(Integer page, Integer perPage);     // สำหรับ Page
    Event getEvent(Long id);
    Event save(Event event);
    Page<Event> getEvents(String title, Pageable page);
    Page<Event> getEventsAnd(String title, String description, Pageable page);
}
