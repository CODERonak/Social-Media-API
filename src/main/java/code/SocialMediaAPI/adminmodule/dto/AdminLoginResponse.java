package code.SocialMediaAPI.adminmodule.dto;

import lombok.Data;

// Admin Login Response DTO
@Data
public class AdminLoginResponse {
    private String email;
    private String token;
}
