package se331.lab.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;
import se331.lab.repository.ParticipantRepository;
import se331.lab.security.user.User;
import se331.lab.security.user.UserRepository;
import se331.lab.security.user.Role;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final UserRepository userRepository;
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
        tempEvent.getParticipants().addAll(List.of(p1, p2, p3));
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

        // ✅ เพิ่มผู้ใช้และเชื่อมกับ organizer
        addUser(org1, org2, org3);
    }

    // ✅ เพิ่มผู้ใช้และเชื่อมกับ organizer
    private void addUser(Organizer org1, Organizer org2, Organizer org3) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .build();

        User user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .enabled(true)
                .build();

        User user3 = User.builder()
                .username("disableUser")
                .password(encoder.encode("disableUser"))
                .firstname("disableUser")
                .lastname("disableUser")
                .email("disableUser@user.com")
                .enabled(false)
                .build();

        // ✅ Roles
        user1.getRoles().add(Role.ROLE_USER);
        user1.getRoles().add(Role.ROLE_ADMIN);
        user2.getRoles().add(Role.ROLE_USER);
        user3.getRoles().add(Role.ROLE_USER);

        // ✅ เชื่อม User ↔ Organizer
        org1.setUser(user1);
        user1.setOrganizer(org1);

        org2.setUser(user2);
        user2.setOrganizer(org2);

        org3.setUser(user3);
        user3.setOrganizer(org3);

        // ✅ Save ทั้งหมด
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        organizerRepository.saveAll(List.of(org1, org2, org3));
    }
}
