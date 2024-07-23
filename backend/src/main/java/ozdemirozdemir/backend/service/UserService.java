package ozdemirozdemir.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ozdemirozdemir.backend.dto.RegistrationData;
import ozdemirozdemir.backend.exception.EmailAlreadyTakenException;
import ozdemirozdemir.backend.models.ApplicationUser;
import ozdemirozdemir.backend.models.Role;
import ozdemirozdemir.backend.repository.RoleRepository;
import ozdemirozdemir.backend.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;

    public ApplicationUser registerUser(RegistrationData registrationData) {
        Set<Role> roles = new HashSet<>();
        roles.add(
                roleRepository
                        .findByAuthority("USER")
                        .orElse(this.roleRepository.save(new Role("USER")))
        );

        ApplicationUser user = new ApplicationUser();
        user.setAuthorities(roles);

        user.setFirstName(registrationData.firstName());
        user.setLastName(registrationData.lastName());
        user.setEmail(registrationData.email());
        user.setDateOfBirth(registrationData.dateOfBirth());

        String name = registrationData.firstName() + registrationData.lastName();
        String tempName = "";
        boolean nameTaken = true;

        do {
            tempName = generateUsername(name);
            if (userRepository.findByUsername(tempName).isEmpty()) {

                nameTaken = false;
            }
        }
        while (nameTaken);

        user.setUsername(tempName);

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException(registrationData.email());
        }
    }


    public Optional<ApplicationUser> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public ApplicationUser updateUser(ApplicationUser user) {
        try {
            return this.userRepository.save(user);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException(user.getEmail());
        }
    }

    public void generateEmailVerification(String username) {
        ApplicationUser user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found!"));

        user.setVerification(generateVerificationNumber());

        mailService.sendEmail(
                user.getEmail(),
                "Your verification code",
                "Here is your verification code: " + user.getVerification()
        );

        this.userRepository.save(user);
    }

    private String generateUsername(String name) {
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return name + generatedNumber;
    }

    private Long generateVerificationNumber() {
        return (long) Math.floor(Math.random() * 100_000_000);
    }
}














