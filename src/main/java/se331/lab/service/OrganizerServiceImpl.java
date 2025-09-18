package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.entity.Organizer;
import se331.lab.repository.OrganizerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    final OrganizerRepository organizerRepository;

    @Override
    public Integer getOrganizerSize() {
        return (int) organizerRepository.count();   // ✅ ใช้ count() ของ repository
    }

    @Override
    public Page<Organizer> getOrganizers(Integer pageSize, Integer page) {
        if (pageSize == null) pageSize = 10;
        if (page == null) page = 1;
        return organizerRepository.findAll(PageRequest.of(page - 1, pageSize));
    }


    @Override
    public Organizer getOrganizer(Long id) {
        return organizerRepository.findById(id).orElse(null); // ✅ หา organizer ตาม id
    }

    // ถ้าจะเพิ่ม organizer
    @Override
    public Organizer save(Organizer organizer) {
        return organizerRepository.save(organizer);   // ✅ ใช้ save ของ JPA
    }
}
