package ozdemirozdemir.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ozdemirozdemir.backend.dto.EmailVerifyData;
import ozdemirozdemir.backend.dto.RegistrationData;
import ozdemirozdemir.backend.dto.UpdatePasswordData;
import ozdemirozdemir.backend.dto.UpdatePhoneData;
import ozdemirozdemir.backend.exception.EmailAlreadyTakenException;
import ozdemirozdemir.backend.exception.EmailFailedToSendException;
import ozdemirozdemir.backend.exception.IncorrectVerificationCodeException;
import ozdemirozdemir.backend.models.ApplicationUser;
import ozdemirozdemir.backend.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerUser(@RequestBody RegistrationData registrationData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(registrationData));
    }

    @PutMapping("/update/phone")
    public ApplicationUser updatePhoneNumber(@RequestBody UpdatePhoneData data){

        ApplicationUser user = userService.findByUsername(data.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + data.username() + " not found!"));

        user.setPhone(data.phone());

        return userService.updateUser(user);
    }

    @PostMapping("/email/code")
    public ResponseEntity<String> createEmailVerification(@RequestParam("username") String username) {
        this.userService.generateEmailVerification(username);
        return ResponseEntity.ok().body("Verification code generated, email sent.");
    }

    @PostMapping("/email/verify")
    public ApplicationUser verifyEmail(@RequestBody EmailVerifyData emailVerifyData) {

        return userService.verifyEmail(emailVerifyData.username(), emailVerifyData.code());

    }

    @PutMapping("/update/password")
    public ApplicationUser updatePassword(@RequestBody UpdatePasswordData updatePasswordData) {
        return userService.setPassword(updatePasswordData.username(), updatePasswordData.password());
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken(EmailAlreadyTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<String> handleUsernameNotFound(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({EmailFailedToSendException.class})
    public ResponseEntity<String> handleEmailFailedToSend(EmailFailedToSendException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({IncorrectVerificationCodeException.class})
    public ResponseEntity<String> handleIncorrectCodeHandler(IncorrectVerificationCodeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
