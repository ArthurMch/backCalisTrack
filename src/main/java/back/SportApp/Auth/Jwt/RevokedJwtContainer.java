package back.SportApp.Auth.Jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class RevokedJwtContainer {
    private static final Set<String> REVOKED_JWT = new HashSet<>();

    private static final Logger logger = (Logger) LoggerFactory.getLogger(RevokedJwtContainer.class);

    private RevokedJwtContainer() {
    }

    public static void revokeToken(final String jwt) {
        logger.info("Revoke JWT token");
        REVOKED_JWT.add(jwt);
    }

    public static boolean isRevoked(final String jwt) {
        return REVOKED_JWT.contains(jwt);
    }

}
