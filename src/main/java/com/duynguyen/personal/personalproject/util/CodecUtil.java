package com.duynguyen.personal.personalproject.util;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.math.BigInteger;

public class CodecUtil {

    public static String encode(long n) {
        return Base64.encode(BigInteger.valueOf(n));
    }

    public static long decode(String url) throws Base64DecodingException {
        return new BigInteger(Base64.decode(url)).longValue();
    }

}
