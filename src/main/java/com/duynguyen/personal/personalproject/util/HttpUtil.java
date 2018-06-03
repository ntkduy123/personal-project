package com.duynguyen.personal.personalproject.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    private static final String DEVELOPMENT = "development";

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static String getBaseURL(String profile) {
        if (profile.equals(DEVELOPMENT)) {
            return "http://localhost:8080/";
        }

        return "https://gentle-refuge-34117.herokuapp.com/";
    }
}
