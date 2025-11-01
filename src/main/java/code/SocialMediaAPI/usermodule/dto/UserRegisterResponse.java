package code.SocialMediaAPI.usermodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// User Register Response
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {
    private String username;
    private String email;
    private String token;
}
