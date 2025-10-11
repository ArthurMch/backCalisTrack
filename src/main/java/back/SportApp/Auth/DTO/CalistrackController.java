package back.SportApp.Auth.DTO;

import back.SportApp.Auth.UserDetailsImpl;
import back.SportApp.User.models.User;
import back.SportApp.User.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class CalistrackController {

    @Autowired
    protected UserService userService;

        protected boolean isLogged() {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return !"anonymousUser".equals(auth.getPrincipal());
        }

        protected User getCurrentUser() {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return userService.getUser(((UserDetailsImpl) auth.getPrincipal()).getEmail());
        }
}
