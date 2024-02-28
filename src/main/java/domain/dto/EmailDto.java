package domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private Long id;

    @NotBlank(message = "Email не должен быть пустым")
    @Email
    private String email;

    @NotBlank
    private UserDto user;
}
