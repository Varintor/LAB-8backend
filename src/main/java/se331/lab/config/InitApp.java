package se331.lab.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;
import se331.lab.entity.Participant;
import se331.lab.repository.ParticipantRepository;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;



    // เพิ่มใน @RequiredArgsConstructor ด้วย
    final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Organizer org1, org2, org3;

        // ✅ Organizer
        org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder().name("ChiangMai").build());

        // ✅ Participant
        Participant p1 = participantRepository.save(Participant.builder().name("Alice").telNo("1").build());
        Participant p2 = participantRepository.save(Participant.builder().name("Bob").telNo("2").build());
        Participant p3 = participantRepository.save(Participant.builder().name("Charlie").telNo("3").build());
        Participant p4 = participantRepository.save(Participant.builder().name("David").telNo("4").build());
        Participant p5 = participantRepository.save(Participant.builder().name("Emma").telNo("5").build());

        Event tempEvent;

        // ✅ Event 1
        tempEvent = Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petsAllowed(false)
                .build();
        tempEvent.setOrganizer(org1);
        tempEvent.getParticipants().addAll(List.of(p1, p2, p3)); // มีอย่างน้อย 3 คน
        org1.getOwnEvents().add(tempEvent);
        eventRepository.save(tempEvent);

        // ✅ Event 2
        tempEvent = Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petsAllowed(false)
                .build();
        tempEvent.setOrganizer(org1);
        tempEvent.getParticipants().addAll(List.of(p1, p2, p3, p4));
        org1.getOwnEvents().add(tempEvent);
        eventRepository.save(tempEvent);

        // ✅ Event 3
        tempEvent = Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petsAllowed(false)
                .build();
        tempEvent.setOrganizer(org2);
        tempEvent.getParticipants().addAll(List.of(p1, p2, p3, p5));
        org2.getOwnEvents().add(tempEvent);
        eventRepository.save(tempEvent);

        // ✅ Event 4
        tempEvent = Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petsAllowed(true)
                .build();
        tempEvent.setOrganizer(org3);
        tempEvent.getParticipants().addAll(List.of(p2, p3, p4));
        org3.getOwnEvents().add(tempEvent);
        eventRepository.save(tempEvent);
    }

}
