package com.zd1024.withyou.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MySHA512 {

    public String getId(String tableName) {
        String id = tableName + "-" + UUID.randomUUID().toString().replace("-", "");
        return id;
    }


    /**
     * MD5 加密
     */
    public String getMD5(String str) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String substring = new BigInteger(1, md.digest()).toString(16).substring(8, 24);
            return substring;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }
}
