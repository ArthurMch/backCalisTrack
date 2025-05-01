package back.SportApp.Auth;
import back.SportApp.Auth.DTO.UserDetailsDTO;
import back.SportApp.Auth.DTO.request.*;
import back.SportApp.Auth.DTO.response.IsValidLostPasswordResponse;
import back.SportApp.Auth.DTO.response.JwtResponse;
import back.SportApp.Auth.DTO.response.LostPasswordResponse;
import back.SportApp.Auth.DTO.response.ResetPasswordResponse;
import back.SportApp.Auth.Jwt.JwtUtil;
import back.SportApp.Email.EmailService;
import back.SportApp.User.Role;
import back.SportApp.User.User;
import back.SportApp.User.UserService;
import back.SportApp.User.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final EmailService emailService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService, EmailService emailService, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailsDTO> register(@RequestBody User user) {
        System.out.println("in the registrer method");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new UserDetailsDTO(savedUser.getEmail(), savedUser.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        System.out.println("Attempt to log in");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), loginRequest.getEmail()));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        logger.info(token);
        Map<String, Boolean> response = new HashMap<>();
        try {
            // Validate the token
            jwtUtil.extractAllClaims(token); // Throws an exception if invalid
            response.put("isValid", true);
        } catch (Exception e) {
            response.put("isValid", false);
        }
        return ResponseEntity.ok(response);
    }



    @PostMapping("/lost-password")
    public ResponseEntity<LostPasswordResponse> lostPassword(
            @Valid @RequestBody final EmailLocaleCodeRequest lostPasswordRequest)
            throws MessagingException, IOException, InterruptedException {
        final String token = userService.startLostPassword(lostPasswordRequest.getEmail());
        if (StringUtils.isNotEmpty(token)) {
            final String link = "http://localhost:8081" + "/reset-password?token=" + token;
            emailService.sendLostPasswordEmail(lostPasswordRequest.getEmail(),
                    link);
        } else {
            // Feinter le délai de traitement trop rapide si une adresse mail qui n'existe
            // pas
            Thread.sleep(1000);
        }
        final LostPasswordResponse response = new LostPasswordResponse();
        response.setSuccess(true);
        response.setExpirationMinute(20);
        return ResponseEntity.ok(response);
    }


    @PreAuthorize("isAnonymous()")
    @PostMapping("/is-valid-lost-password")
    public ResponseEntity<IsValidLostPasswordResponse> isValidLostPassword(
            @Valid @RequestBody final IsValidTokenRequest isValidLostPasswordRequest) {
        final IsValidLostPasswordResponse ret = new IsValidLostPasswordResponse();
        ret.setValid(userService.isValidLostPasswordToken(isValidLostPasswordRequest.getToken()));
        final Optional<User> opt = userService.getUserByLostPasswordToken(isValidLostPasswordRequest.getToken());
        if (opt.isPresent()) {
            ret.setEmail(opt.get().getEmail());
            ret.setUsername(opt.get().getFirstname() + " " + opt.get().getLastname());
        }
        return ResponseEntity.ok(ret);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordResponse> resetLostPassword(
            @Valid @RequestBody final ResetPasswordRequest resetPasswordRequest) {
        final ResetPasswordResponse resetPasswordResponse = new ResetPasswordResponse();
        resetPasswordResponse.setStatus(
                userService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getPassword()));
        return ResponseEntity.ok(resetPasswordResponse);
    }

}

