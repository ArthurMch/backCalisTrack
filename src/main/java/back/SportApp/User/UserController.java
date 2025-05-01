package back.SportApp.User;


import back.SportApp.Auth.DTO.response.ResetPasswordResponse;
import back.SportApp.Auth.DTO.response.ResetPasswordStatus;
import back.SportApp.Auth.Jwt.JwtUtil;
import back.SportApp.User.DTO.PasswordUpdateRequest;
import back.SportApp.User.DTO.ProfileUpdateRequest;
import back.SportApp.User.DTO.ProfileUpdateResponse;
import back.SportApp.User.DTO.UserDTO;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") Integer id) {
        try {
            final User user = userService.findById(id);
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return userService.deleteById(id);
    }

    @PostMapping("/update")
    public ResponseEntity<ProfileUpdateResponse> updateProfile(final HttpServletRequest request,
                                                               final Principal principal, @Valid @RequestBody final ProfileUpdateRequest profileUpdatePayload) {
        final ProfileUpdateResponse response = new ProfileUpdateResponse();
        final User user = userService.getUser(principal.getName());
        final boolean exists = userService.existsByEmailExceptSelf(user.getId(), profileUpdatePayload.getEmail());
        if (!exists) {
            boolean success = userService.editProfileInfo(principal.getName(), profileUpdatePayload.getPhone(), profileUpdatePayload.getFirstname(), profileUpdatePayload.getLastname());

            if (!profileUpdatePayload.getEmail().equals(principal.getName())
                    && !StringUtils.isEmpty(profileUpdatePayload.getPassword())) {
                final Authentication authenticationOriginal = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(principal.getName(),
                                profileUpdatePayload.getPassword()));
                final String originalJwt = jwtUtil.parseJwt(request);
                if (authenticationOriginal != null) {
                    success &= userService.editEmail(principal.getName(),  profileUpdatePayload.getEmail());

                    if (success) {
                        final Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(profileUpdatePayload.getEmail(),
                                        profileUpdatePayload.getPassword()));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        final String newJwt = jwtUtil.generateToken(profileUpdatePayload.getEmail());
                        response.setAccessToken(newJwt);
                        jwtUtil.revokeJwt(request);
                    }
                }
            }
            response.setSuccess(success);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-password")
    public ResponseEntity<ResetPasswordResponse> updatePassword(final Principal principal,
                                                                @Valid @RequestBody final PasswordUpdateRequest passwordUpdatePayload) {
        final ResetPasswordResponse response = new ResetPasswordResponse();
        final ResetPasswordStatus status = userService.editPassword(principal.getName(),
                passwordUpdatePayload.getPassword());
        response.setStatus(status);

        return ResponseEntity.ok(response);
    }
}
