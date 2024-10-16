package back.SportApp.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> findAll();
    User findById(Long id);
    User update(User user);
    String deleteById(Long id);
}
