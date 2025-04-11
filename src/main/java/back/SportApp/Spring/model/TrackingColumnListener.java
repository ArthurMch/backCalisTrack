package back.SportApp.Spring.model;

import back.SportApp.Auth.UserDetailsImpl;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class TrackingColumnListener<T extends CalistrackEntity> {

    @PrePersist
    public void prePersist(final T entity) {
        entity.setCreationDate(getCurrentDate());
        entity.setCreationUser(getCurrentUserLogin());
        entity.setCreationReason("Runtime insert");
    }

    @PreUpdate
    public void preUpdate(final T entity) {
        entity.setModificationDate(getCurrentDate());
        entity.setModificationUser(getCurrentUserLogin());
        entity.setModificationReason("Runtime update");
    }

    private Date getCurrentDate() {
        return new Date();
    }

    private String getCurrentUserLogin() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username;
        if (auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
            final UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
                username = userDetails.getEmail();
        } else {
            username = "No Logged User";
        }
        return username;
    }
}
