package com.prod.auth.Controller;

import com.prod.auth.Dto.LoginRequestDto;
import com.prod.auth.Dto.SigninRequestDto;
import com.prod.auth.Entity.User;
import com.prod.auth.Repository.UserDetailRepo;
import com.prod.auth.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final UserDetailRepo userDetailRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public AuthController(AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          UserDetailRepo userDetailRepo,
                          UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailRepo = userDetailRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userService = userService;
    }


    @GetMapping
    public String homePage() {
        return "Hello User";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) throws Exception {

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                            loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

       if(!authentication.isAuthenticated()) {
           throw new Exception("error");
       }
        return null;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SigninRequestDto signinRequest) throws Exception {
        boolean doesUserExist = userService.existsByEmail(signinRequest.getEmail()) ||
                userService.existsByUsername(signinRequest.getUsername());

        if(doesUserExist) {
            throw new Exception("register error" + signinRequest.getUsername() + " already exists");
        }

        User user = User.builder()
                .email(signinRequest.getEmail())
                .username(signinRequest.getUsername())
                .password(passwordEncoder.encode(signinRequest.getPassword()))
                .build();

        userDetailRepo.save(user);

        return ResponseEntity.ok(("User registered successfully!"));
    }

    @GetMapping("/Signout")
    public ResponseEntity<?> signout() {
       //Clear cookie and return
        return null;

    }
}
