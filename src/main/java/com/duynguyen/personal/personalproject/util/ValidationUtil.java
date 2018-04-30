package com.duynguyen.personal.personalproject.util;

import com.google.common.base.Strings;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidationUtil {

    public static boolean isEmpty(String value) {
        return Strings.isNullOrEmpty(value);
    }

    public static boolean isEmail(String value) {
        try {
            InternetAddress email = new InternetAddress(value);
            email.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    public static boolean isMatch(String value1, String value2) {
        return value1.equals(value2);
    }
}
