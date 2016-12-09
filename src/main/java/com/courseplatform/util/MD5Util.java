package com.courseplatform.util;


import com.courseplatform.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 采用MD5加密解密
 *
 * @Author chen cy
 * Created by ye on 2016/12/9.
 */
public class MD5Util {
    private static final Logger LOG = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 对用户的账号,密码,类型进行组合加密
     *
     * @param account
     *         账号
     * @param password
     *         密码
     * @param type
     *         类型(1:学生,0:老师)
     * @return 加密过后的字符串
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 20:10
     */
    public static String getMD5(String account, String password, int type) {
        String str = account + password + type;
        return getMD5(str);
    }

    /**
     * 得到MD5加密的字符串
     *
     * @param str
     *         要进行加密的字符串
     * @return 加密过后的字符串
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 20:08
     */
    public static String getMD5(String str) {
        // 生成一个MD5摘要
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            // 计算MD5
            messageDigest.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("生成MD5错误", e.getMessage());
        }
        return "";
    }

    /**
     * 对user进行加密
     *
     * @param user
     * @return 加密过后的字符串
     * @author ye [15622797401@163.com]
     * @date 2016/12/9 20:09
     */
    public static String getMD5(User user) {
        return getMD5(user.getAccount(), user.getPassword(), user.getType());
    }

    public static void main(String[] args) {
        String str1 = "131544215" + "131544215" + "1";
        String str2 = "131544245" + "131544245" + "1";
        System.out.println("str1 = " + getMD5(str1));
        System.out.println("str2 = " + getMD5(str2));
        // 131544215 : 5a66f14e226173b3eb135e65484f165f
        // 131544245 : 12c7e8ab44382d8158179ab386f1110c
    }
}