package se331.lab.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;


import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;   // อย่ากำหนดค่าเอง ให้ DB auto generate

    String category;
    String title;
    String description;
    String location;
    String date;
    String time;
    Boolean petsAllowed;
    @ManyToOne
    @JoinColumn(name = "organizer_id") // foreign key ไปที่ organizer
    @JsonManagedReference
    Organizer organizer;

    @ManyToMany
    @JoinTable(
            name = "participant_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    List<Participant> participants;


}
