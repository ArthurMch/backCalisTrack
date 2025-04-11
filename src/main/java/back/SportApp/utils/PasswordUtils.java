package back.SportApp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordUtils {

    static PasswordEncoder encoder = new BCryptPasswordEncoder();

    private PasswordUtils() {

    }

    public static String cryptPassword(final String password) {
        return encoder.encode(password);
    }

    public static boolean matchPassword(final String rawPassword, final String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    public static boolean isPatternRespected(final String password) {
        final Pattern p = Pattern.compile(
                "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!\\.,;\\+:§£¤%*=|`\\\\#?&\\(\\{\\[\\)\\]\\}~\\/\\^_-]).{10,}");
        final Matcher m = p.matcher(password);
        return m.matches();
    }
}
