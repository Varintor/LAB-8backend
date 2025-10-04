package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    Organizer save(Organizer organizer);
    Page<Organizer> getOrganizers(Integer page, Integer pageSize);
    List<Organizer> getAllOrganizer();
    Organizer getOrganizer(Long id);
}
