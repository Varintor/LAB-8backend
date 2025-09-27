package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;

import java.util.Optional;

public interface EventDao {
    Integer getEventSize();
    Page<Event> getEvents(Integer page, Integer perPage);
    Event getEvent(Long id);
    Event save(Event event);
    Page<Event> getEvents(String name , Pageable page);
    Page<Event> getEventsAnd(String title, String description, Pageable page);
}
