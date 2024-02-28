package domain.dto;

import com.effectiveMobile.testTask.validation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    private Long id;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @PhoneNumber(message = "Не верный формат номера телефона, введите номер в формате +7 ХХХ ХХХ ХХ ХХ")
    private String phone;

    @NotBlank
    private UserDto user;
}
