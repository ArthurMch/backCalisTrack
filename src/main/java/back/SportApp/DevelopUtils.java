package back.SportApp;

import back.SportApp.User.models.User;
import back.SportApp.User.services.UserService;

public class DevelopUtils {

    public static User getUser(UserService userService) {
        return userService.findById(1);
    }
}
