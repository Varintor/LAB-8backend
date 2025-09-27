package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Organizer;
import java.util.List;

public interface OrganizerService {
//    Integer getOrganizerSize();
//    Organizer save(Organizer organizer);
//    List<Organizer> getAllOrganizers();
    Page<Organizer> getOrganizer(Integer pageSize ,Integer page);
    List<Organizer> getAllOrganizer();
}

