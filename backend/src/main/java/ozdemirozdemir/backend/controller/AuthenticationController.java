package ozdemirozdemir.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ozdemirozdemir.backend.models.ApplicationUser;
import ozdemirozdemir.backend.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerUser(@RequestBody ApplicationUser user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
    }


}
