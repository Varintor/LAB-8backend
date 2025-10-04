package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Organizer;
import se331.lab.repository.OrganizerRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrganizerDaoImpl implements OrganizerDao {
    final OrganizerRepository organizerRepository;

    @Override
    public Optional<Organizer> findById(Long id) {
        return organizerRepository.findById(id);
    }

    @Override
    public Page<Organizer> getOrganizers(Pageable pageable) {
        return organizerRepository.findAll(pageable);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerRepository.findById(id).orElse(null);
    }

    @Override
    public Organizer save(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
}


//    @Override
//    public Integer getOrganizerSize() {
//        return organizerList.size();
//    }
//
//    @Override
//    public List<Organizer> getOrganizerList(Integer pageSize, Integer page) {
//        pageSize = pageSize == null ? organizerList.size() : pageSize;
//        page = page == null ? 1 : page;
//        int firstIndex = (page - 1) * pageSize;
//        return organizerList.subList(
//                firstIndex,
//                Math.min(firstIndex + pageSize, organizerList.size())
//        );
//    }
//
//    @Override
//    public Organizer getOrganizer(Long id) {
//        return organizerList.stream()
//                .filter(o -> o.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }

