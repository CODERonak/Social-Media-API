package code.SocialMediaAPI.usermodule.model;

import code.SocialMediaAPI.core.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/*
 * User Entity for saving the user details 
 */
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
