package back.SportApp.User;


import back.SportApp.Auth.DTO.response.ResetPasswordStatus;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User getUser(final String email);
    public User getUserWithoutException(final String email);
    User create(User user);
    List<User> findAll();
    User findById(Integer id);
    User update(User user);
    String deleteById(Integer id);
    public String startLostPassword(final String email);
    public String manageLostPasswordToken(final User user, final int expiration);
    public boolean isValidLostPasswordToken(final String token);
    public Optional<User> getUserByLostPasswordToken(final String token);
    public ResetPasswordStatus resetPassword(final String token, final String password);
    public boolean existsByEmailExceptSelf(int id, String email);
    public boolean existsByEmail(String email);
    boolean editProfileInfo(String loginEmail, String phone, String firstname, String lastname);
    public boolean editEmail(final String loginEmail, final String newEmail);
    ResetPasswordStatus editPassword(String loginEmail, String password);
}
