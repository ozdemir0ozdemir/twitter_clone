package ozdemirozdemir.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ozdemirozdemir.backend.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByAuthority(String authority);
}
