package theo.conzi.scholarship.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String className;
    private String squadName;

}
