package se331.lab.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;

    @OneToMany(mappedBy = "organizer")
    @Builder.Default
    @JsonBackReference
    List<Event> ownEvents = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "organizer_images", joinColumns = @JoinColumn(name = "organizer_id"))
    @Column(name = "image_url")
    List<String> images = new ArrayList<>();
}
