package theo.conzi.scholarship.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequestDTO {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "duration is mandatory")
    private String duration;

}
