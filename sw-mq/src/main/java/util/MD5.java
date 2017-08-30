package util;

import java.security.MessageDigest;

/**
 *
 */
public class MD5 {
    private final static char[] hexDigits = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String bytes2hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int t;
        for (int i = 0; i < 16; i++) { // 16 == bytes.length;
            t = bytes[i];
            if (t < 0) {
                t += 256;
            }
            sb.append(hexDigits[(t >>> 4)]);
            sb.append(hexDigits[(t % 16)]);
        }
        return sb.toString();
    }

    public static String getMd5String(String strSrc) {
        try {
            // 确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加密后的字符串
            return bytes2hex(md5.digest(strSrc.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
