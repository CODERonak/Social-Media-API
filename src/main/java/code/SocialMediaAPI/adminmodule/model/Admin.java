package code.SocialMediaAPI.adminmodule.model;

import java.util.UUID;

import code.SocialMediaAPI.core.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

/*
 * Admin Entity for saving the admin details in the database
 * Admin will have a lot of control over the platform
 */
@Entity
@Data
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
