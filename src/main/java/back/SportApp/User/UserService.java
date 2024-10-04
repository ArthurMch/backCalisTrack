package back.SportApp.User;

import java.util.List;

public interface UserService {
    User creer(User user);
    List<User> lire();
    User lireById(Long id);
    User modifier(User user);
    String supprimer(Long id);
}
