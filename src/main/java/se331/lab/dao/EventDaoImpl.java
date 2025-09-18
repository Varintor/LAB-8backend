package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class EventDaoImpl implements EventDao {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>(List.of(
                Event.builder()
                        .id(123L)
                        .category("animal welfare")
                        .title("Cat Adoption Day")
                        .description("Find your new feline friend at our Cat Adoption Day event!")
                        .location("Meow Town")
                        .date("January 28,2022")
                        .time("12:00")
                        .petsAllowed(true)
                        .organizer("Kat Laydee")
                        .build(),
                Event.builder()
                        .id(456L)
                        .category("food")
                        .title("Community Gardening")
                        .description("Join us as we tend to the community edibleplants.")
                        .location("Flora City")
                        .date("March 14,2022")
                        .time("10:00")
                        .petsAllowed(true)
                        .organizer("Fern Pollin")
                        .build(),
                Event.builder()
                        .id(789L)
                        .category("sustainability")
                        .title("Beach Cleanup")
                        .description("Help pick up trash along the shore.")
                        .location("Playa Del Carmen")
                        .date("July 22, 2022")
                        .time("11:00")
                        .petsAllowed(false)
                        .organizer("Carey Wales")
                        .build(),
                Event.builder()
                        .id(1001L)
                        .category("animal welfare")
                        .title("Dog Adoption Day")
                        .description("Find your new canine friend at this event.")
                        .location("Doggo Ville")
                        .date("February 20,2022")
                        .time("13:00")
                        .petsAllowed(true)
                        .organizer("Woof Gang")
                        .build(),
                Event.builder()
                        .id(1002L)
                        .category("food")
                        .title("Farmers Market")
                        .description("Fresh produce and artisanal goods from local farmers and makers.")
                        .location("Harvest Square")
                        .date("April 10,2022")
                        .time("09:00")
                        .petsAllowed(false)
                        .organizer("Agri Culture")
                        .build(),
                Event.builder()
                        .id(1003L)
                        .category("sustainability")
                        .title("Recycling Workshop")
                        .description("Learn how to recycle effectively and reduce waste.")
                        .location("Eco Center")
                        .date("August 15,2022")
                        .time("14:00")
                        .petsAllowed(false)
                        .organizer("Green Earth")
                        .build()
        ));
    }

    @Override
    public Integer getEventSize() {
        return eventList.size();
    }

    @Override
    public Page<Event> getEvents(Integer page, Integer perPage) {
        perPage = (perPage == null || perPage <= 0) ? eventList.size() : perPage;
        page = (page == null || page <= 0) ? 1 : page;

        int firstIndex = (page - 1) * perPage;
        int lastIndex = Math.min(firstIndex + perPage, eventList.size());

        return new PageImpl<>(
                eventList.subList(firstIndex, lastIndex),
                PageRequest.of(page - 1, perPage),
                eventList.size()
        );
    }

    @Override
    public Event getEvent(Long id) {
        return eventList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event save(Event event) {
        event.setId(eventList.get(eventList.size() - 1).getId() + 1);
        eventList.add(event);
        return event;
    }
}
