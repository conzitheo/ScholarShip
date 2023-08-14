package theo.conzi.scholarship.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "idClass is mandatory")
    private Long idClass;

}
