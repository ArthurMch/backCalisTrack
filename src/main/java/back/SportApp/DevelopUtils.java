package back.SportApp;

import back.SportApp.User.User;
import back.SportApp.User.UserService;

public class DevelopUtils {

    public static User getUser(UserService userService) {
        return userService.findById(1);
    }
}
