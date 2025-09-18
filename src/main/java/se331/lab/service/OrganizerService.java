package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Organizer;
import java.util.List;

public interface OrganizerService {
    Integer getOrganizerSize();
    Page<Organizer> getOrganizers(Integer pageSize, Integer page);  // แก้ตรงนี้
    Organizer getOrganizer(Long id);
    Organizer save(Organizer organizer);
}

