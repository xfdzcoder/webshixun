package com.example.webshixun.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.MD5;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;


public class PasswordUtil {

    private PasswordUtil() {
    }

    //随机盐值字节数
    private static final int SALT_BYTE_LENGTH = 20;

    private static final Charset CHARSET = Charset.forName("UTF-8");

    //生成不可逆16进制加密密码
    public static String encrypt(String password) {

        //创建一个SecureRandom对象，它提供加密的强随机数生成器 (RNG)
        SecureRandom secureRandom = new SecureRandom();
        //创建一个字节数组salt，长度为SALT_BYTE_LENGTH，用于存储盐值
        byte salt[] = new byte[SALT_BYTE_LENGTH];
        //使用secureRandom对象生成随机数并填充到salt数组
        secureRandom.nextBytes(salt);
        //创建一个MD5对象，使用salt作为参数
        MD5 md5 = new MD5(salt);
        //调用md5对象的digest方法，对密码进行哈希处理，并将结果存储在digest数组中
        byte[] digest = md5.digest(password, CHARSET);
        //填充前20个字节为盐值，校验密码时候需要取出
        //将salt和digest数组合并，存储在pwd数组中。前20个字节是盐值，用于密码校验
        byte[] pwd = ArrayUtil.addAll(salt, digest);
        //将pwd数组转换为十六进制字符串，并返回
        return HexUtil.encodeHexStr(pwd);
    }

    public static boolean valid(String password, String encryptedPassword) {
        //将加密后的密码encryptedPassword从十六进制字符串转换为字节数组encryptedPwd
        byte[] encryptedPwd = HexUtil.decodeHex(encryptedPassword);
        //取出前20个字节盐值
        //从encryptedPwd数组中取出前SALT_BYTE_LENGTH个字节作为盐值salt
        byte[] salt = ArrayUtil.sub(encryptedPwd, 0, SALT_BYTE_LENGTH);
        //20字节后为真正MD5后密码
        //从encryptedPwd数组中取出从SALT_BYTE_LENGTH开始到数组结束的部分作为真正的加密密码pwd
        byte[] pwd = ArrayUtil.sub(encryptedPwd, SALT_BYTE_LENGTH, encryptedPwd.length);
        //创建一个MD5对象，使用salt作为参数
        MD5 md5 = new MD5(salt);
        //调用md5对象的digest方法，对密码进行哈希处理，并将结果存储在digest数组中
        byte[] digest = md5.digest(password, CHARSET);
        //比较digest数组和pwd数组是否相等，如果相等则返回true，否则返回false
        return Arrays.equals(digest, pwd);
    }

}
