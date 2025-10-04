package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.dao.OrganizerDao;
import se331.lab.entity.Organizer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    final OrganizerDao organizerDao;

    @Override
    public Organizer save(Organizer organizer) {
        return organizerDao.save(organizer);
    }

    @Override
    public Page<Organizer> getOrganizers(Integer page, Integer pageSize) {
        return organizerDao.getOrganizers(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public List<Organizer> getAllOrganizer() {
        return organizerDao.getOrganizers(PageRequest.of(0, Integer.MAX_VALUE)).getContent();
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerDao.getOrganizer(id);
    }
}
