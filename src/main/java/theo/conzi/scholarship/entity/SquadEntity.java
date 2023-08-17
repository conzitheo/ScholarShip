package theo.conzi.scholarship.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "squad")
public class SquadEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "squad")
    private List<StudentEntity> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_class")
    private ClassEntity classEntity;

}

