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
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private String duration;

    @OneToMany(mappedBy = "classEntity")
    private List<StudentEntity> students;

    @OneToMany(mappedBy = "classEntity")
    private List<SquadEntity> squads;


    @ManyToMany(mappedBy = "classes")
    private List<OrganizerEntity> organizers;

}