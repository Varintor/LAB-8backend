package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;
import se331.lab.repository.EventRepository;

@Repository
@Primary
@RequiredArgsConstructor
public class EventDaoImpl implements EventDao {
    final EventRepository eventRepository;

    @Override
    public Integer getEventSize() {
        return (int) eventRepository.count();
    }

    @Override
    public Page<Event> getEvents(Integer page, Integer perPage) {
        return eventRepository.findAll(org.springframework.data.domain.PageRequest.of(page - 1, perPage));
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    // 💡 ส่วนนี้คือ method ใหม่ ที่หาได้ทั้ง title และ description
    @Override
    public Page<Event> getEvents(String title, Pageable page) {
        return eventRepository
                .findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrOrganizer_NameIgnoreCaseContaining(
                        title, title, title, page
                );
    }

    @Override
    public Page<Event> getEventsAnd(String title, String description, Pageable page) {
        return eventRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrOrganizer_NameIgnoreCaseContaining(
                title, description, title, page);
    }
}
