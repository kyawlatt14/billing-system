package net.kkl.billingsystem.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Username must not be null...")
    private String username;
    @NotBlank(message = "Email must not be null...")
    private String email;
    @NotBlank(message = "PhoneNumber must not be null...")
    private String phoneNumber;
    @NotBlank(message = "Password must not be null...")
    private String password;
}
