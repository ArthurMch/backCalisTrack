package back.SportApp.utils;

import io.micrometer.common.util.StringUtils;

public class Utility {
    private Utility() {

    }

    public static String getSiteBaseURL() {
        final String siteBaseUrl = System.getenv("SITE_BASE_URL");
        if (StringUtils.isEmpty(siteBaseUrl)) {
            throw new IllegalArgumentException("Empty value for env variable SITE_BASE_URL");
        }
        return siteBaseUrl;
    }
}

