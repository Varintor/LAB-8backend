package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Organizer;

import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = List.of(
                Organizer.builder()
                        .id(123L)
                        .organizerName("Kat Laydee")
                        .address("Meow Town")
                        .build(),
                Organizer.builder()
                        .id(456L)
                        .organizerName("Fern Pollin")
                        .address("Flora City")
                        .build(),
                Organizer.builder()
                        .id(789L)
                        .organizerName("Carey Wales")
                        .address("Playa Del Carmen")
                        .build(),
                Organizer.builder()
                        .id(1001L)
                        .organizerName("Woof Gang")
                        .address("Doggo Ville")
                        .build(),
                Organizer.builder()
                        .id(1002L)
                        .organizerName("Agri Culture")
                        .address("Harvest Square")
                        .build(),
                Organizer.builder()
                        .id(1003L)
                        .organizerName("Green Earth")
                        .address("Eco Center")
                        .build()
        );
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizerList(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizerList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return organizerList.subList(
                firstIndex,
                Math.min(firstIndex + pageSize, organizerList.size())
        );
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
