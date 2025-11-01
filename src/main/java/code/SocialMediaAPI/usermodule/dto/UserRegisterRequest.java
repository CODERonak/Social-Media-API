package code.SocialMediaAPI.usermodule.dto;

import lombok.Data;

// User Register Request 
@Data
public class UserRegisterRequest {
    private String username;
    private String email;
    private String password;
}
