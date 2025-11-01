package code.SocialMediaAPI.usermodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// User Login Response
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private String username;
    private String email;
    private String token;
}
