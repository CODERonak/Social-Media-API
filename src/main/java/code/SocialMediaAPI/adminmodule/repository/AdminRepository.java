package code.SocialMediaAPI.adminmodule.repository;

import code.SocialMediaAPI.adminmodule.model.Admin;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Admin Repository interface to for database operations
@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

}
