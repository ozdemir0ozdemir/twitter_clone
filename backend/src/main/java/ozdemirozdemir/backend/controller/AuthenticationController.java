package ozdemirozdemir.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ozdemirozdemir.backend.dto.RegistrationData;
import ozdemirozdemir.backend.exception.EmailAlreadyTakenException;
import ozdemirozdemir.backend.models.ApplicationUser;
import ozdemirozdemir.backend.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken(EmailAlreadyTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerUser(@RequestBody RegistrationData registrationData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registrationData));
    }


}
