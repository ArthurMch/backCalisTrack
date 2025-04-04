package back.SportApp.User;


import java.util.List;

public interface UserService {
    public User getUser(final String email);
    public User getUserWithoutException(final String email);
    User create(User user);
    List<User> findAll();
    User findById(Integer id);
    User update(User user);
    String deleteById(Integer id);
}
