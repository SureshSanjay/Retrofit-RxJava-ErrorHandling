package com.example.user.kotlinwithrxjanddaggermvp.utils;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by user on 3/11/18.
 */

public class UtilsJava {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String hmacSha1(String signatureString, String secretKey) {
        System.out.println("Signature " + signatureString);
        System.out.println("SecretKey " + secretKey);
        byte[] bytes = new byte[0];
        try {
            String type = "HmacSHA1";
            SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes("UTF-8"), type);
            Mac mac = Mac.getInstance(type);
            mac.init(secret);
            bytes = mac.doFinal(signatureString.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToHex(bytes);
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String getTokenDateFromKey(String data, String key) {
        try {
            JSONObject jsonObject2 = new JSONObject(data);
            return jsonObject2.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
