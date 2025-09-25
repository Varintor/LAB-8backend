package se331.lab.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;   // ✅ เพิ่มเข้ามา

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // ✅ สร้าง organizer ก่อน
        Organizer camt = organizerRepository.save(
                Organizer.builder()
                        .organizerName("CAMT")
                        .address("CMU Campus")
                        .build()
        );
        Organizer cmu = organizerRepository.save(
                Organizer.builder()
                        .organizerName("CMU")
                        .address("Chiang Mai")
                        .build()
        );
        Organizer chiangMai = organizerRepository.save(
                Organizer.builder()
                        .organizerName("Chiang Mai")
                        .address("Ping River")
                        .build()
        );
        Organizer chiangMaiMunicipality = organizerRepository.save(
                Organizer.builder()
                        .organizerName("Chiang Mai Municipality")
                        .address("City Hall")
                        .build()
        );

        // ✅ Event ที่โยงกับ Organizer
        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petsAllowed(false)
                .organizer(camt)   // ใช้ entity ไม่ใช่ string
                .build());

        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petsAllowed(false)
                .organizer(cmu)
                .build());

        eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petsAllowed(false)
                .organizer(chiangMai)
                .build());

        eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petsAllowed(true)
                .organizer(chiangMaiMunicipality)
                .build());
    }
}
