package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.Organizer;
import java.util.List;

public interface OrganizerDao {
//    Integer getOrganizerSize();
//    List<Organizer> getOrganizerList(Integer pageSize, Integer page);
//    Organizer getOrganizer(Long id);
    Page<Organizer> getOrganizer(Pageable pageRequest);
}
