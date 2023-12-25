package brianbig.transcend.api.request;

import brianbig.transcend.entities.enums.ClassForm;
import jakarta.validation.constraints.NotBlank;

public record StreamCreateRequest(@NotBlank ClassForm classForm, @NotBlank String name) {
}
