package com.ayselabdulzade.usermanagementservice.dto.response;

import com.ayselabdulzade.usermanagementservice.entity.UserEntity;
import com.ayselabdulzade.usermanagementservice.enums.PhonePrefix;
import com.ayselabdulzade.usermanagementservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private PhonePrefix phonePrefix;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponse(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.phonePrefix = user.getPhonePrefix();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
