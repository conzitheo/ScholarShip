package theo.conzi.scholarship.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "organizer")
public class OrganizerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "organizer_class", joinColumns = {
            @JoinColumn(name = "id_organizer")}, inverseJoinColumns = {
            @JoinColumn(name = "id_class")
    })
    private List<ClassEntity> classes;

}