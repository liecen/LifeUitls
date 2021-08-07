package com.liecen.lifeutils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @desc:
 * @author: zhang
 * @date: 2019/6/28
 */
public class EncryUtil {




    public static  String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try {
                md.update(plainText.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //System.out.println("result: " + buf.toString());// 32位的加密
            //System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
