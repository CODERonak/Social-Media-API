package code.SocialMediaAPI.usermodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import code.SocialMediaAPI.usermodule.model.User;

import java.util.Optional;
import java.util.UUID;

// User Repository interface to for database operations
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
