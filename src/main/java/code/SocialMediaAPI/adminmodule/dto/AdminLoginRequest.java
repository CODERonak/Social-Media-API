package code.SocialMediaAPI.adminmodule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Admin Login Request DTO
@Data
public class AdminLoginRequest {

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 8, message = "Password must be between 8 and 50 characters")
    private String password;
}
