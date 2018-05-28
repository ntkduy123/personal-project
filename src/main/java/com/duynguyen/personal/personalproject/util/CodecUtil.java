package com.duynguyen.personal.personalproject.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.w3c.dom.Text;

import java.math.BigInteger;

public class CodecUtil {

    public String encode(long n) {
        return Base64.encode(BigInteger.valueOf(n));
    }

    public long decode(String url) {
        return Long.valueOf(Base64.decodeBigIntegerFromText(new Text(url)));
    }

}
