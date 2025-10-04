package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Organizer;
import java.util.List;
import java.util.Optional;

public interface OrganizerDao {
//    Integer getOrganizerSize();
//    List<Organizer> getOrganizerList(Integer pageSize, Integer page);
//    Organizer getOrganizer(Long id);
Page<Organizer> getOrganizers(Pageable pageable);
    Organizer getOrganizer(Long id);
    Organizer save(Organizer organizer);
    Optional<Organizer> findById(Long id);
}
