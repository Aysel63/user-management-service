package com.ayselabdulzade.usermanagementservice.dto.request;

import com.ayselabdulzade.usermanagementservice.enums.PhonePrefix;
import com.ayselabdulzade.usermanagementservice.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Phone prefix is required")
    private PhonePrefix phonePrefix;

    private Role role = Role.USER;
}
