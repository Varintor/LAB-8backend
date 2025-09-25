package se331.lab.entity;

import jakarta.persistence.*;
import lombok.*;
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
    Organizer organizer;

}
