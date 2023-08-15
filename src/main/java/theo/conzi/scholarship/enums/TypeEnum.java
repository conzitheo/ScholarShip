package theo.conzi.scholarship.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TypeEnum {
    SCRUM_MASTER,
    COORDINATOR,
    INSTRUCTOR;

    public static Optional<TypeEnum> getTypeByName(String name) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
