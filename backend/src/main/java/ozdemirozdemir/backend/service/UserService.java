package ozdemirozdemir.backend.service;

import org.springframework.stereotype.Service;
import ozdemirozdemir.backend.models.ApplicationUser;
import ozdemirozdemir.backend.models.Role;
import ozdemirozdemir.backend.repository.RoleRepository;
import ozdemirozdemir.backend.repository.UserRepository;

import java.util.Set;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public ApplicationUser registerUser(ApplicationUser user){
        Set<Role> roles = user.getAuthorities();
        roles.add(roleRepository.findByAuthority("USER").get());
        user.setAuthorities(roles);

        return userRepository.save(user);
    }
}
