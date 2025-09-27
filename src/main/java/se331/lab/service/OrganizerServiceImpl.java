package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.dao.OrganizerDao;
import se331.lab.entity.Organizer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    //final OrganizerRepository organizerRepository;
    final OrganizerDao organizerDao;
    @Override
    public List<Organizer> getAllOrganizer() {
        return organizerDao.getOrganizer(Pageable.unpaged()).getContent(); // ✅ ใช้ findAll() ของ repository
    }

    @Override
    public Page<Organizer> getOrganizer(Integer pageSize, Integer page) {
        if (pageSize == null) pageSize = 10;
        if (page == null) page = 1;
        return organizerDao.getOrganizer(PageRequest.of(page - 1, pageSize));    }

//    @Override
//    public Integer getOrganizerSize() {
//        return (int) organizerRepository.count();   // ✅ ใช้ count() ของ repository
//    }
//
//    @Override
//    public Page<Organizer> getOrganizers(Integer pageSize, Integer page) {
//        if (pageSize == null) pageSize = 10;
//        if (page == null) page = 1;
//        return organizerRepository.findAll(PageRequest.of(page - 1, pageSize));
//    }
//
//
//    @Override
//    public Organizer getOrganizer(Long id) {
//        return organizerRepository.findById(id).orElse(null); // ✅ หา organizer ตาม id
//    }
//
//    // ถ้าจะเพิ่ม organizer
//    @Override
//    public Organizer save(Organizer organizer) {
//        return organizerRepository.save(organizer);   // ✅ ใช้ save ของ JPA
//    }
}
