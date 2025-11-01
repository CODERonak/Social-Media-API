package code.SocialMediaAPI.usermodule.dto;

import lombok.Data;

// User Login Request 
@Data
public class UserLoginRequest {
    private String login;
    private String password;
}
