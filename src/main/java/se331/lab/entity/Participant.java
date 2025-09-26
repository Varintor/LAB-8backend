package se331.lab.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    String name;
    String telNo;

    @ManyToMany(mappedBy = "participants")
    List<Event> eventHistory;
}
