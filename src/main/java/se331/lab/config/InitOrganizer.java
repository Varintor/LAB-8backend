//package se331.lab.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//import se331.lab.entity.Organizer;
//import se331.lab.repository.OrganizerRepository;
//
//@Component
//@RequiredArgsConstructor
//public class InitOrganizer implements ApplicationListener<ApplicationReadyEvent> {
//    final OrganizerRepository organizerRepository;
//
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        organizerRepository.save(Organizer.builder()
//                .name("CAMT")
//                //.address("Chiang Mai University")
//                .build());
//
//        organizerRepository.save(Organizer.builder()
//                .name("CMU")
//               // .address("Chiang Mai")
//                .build());
//
//        organizerRepository.save(Organizer.builder()
//                .name("Chiang Mai")
//               // .address("Ping River Area")
//                .build());
//
//        organizerRepository.save(Organizer.builder()
//                .name("Chiang Mai Municipality")
//               // .address("City Hall")
//                .build());
//    }
//}
