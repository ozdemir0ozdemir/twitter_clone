package ozdemirozdemir.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ozdemirozdemir.backend.models.ApplicationUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {

    Optional<ApplicationUser> findByUsername(String username);
}
