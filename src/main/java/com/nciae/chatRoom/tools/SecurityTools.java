package com.nciae.chatRoom.tools;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class SecurityTools {

    /**
     * 将明文密码通过md5加密转换为密文方法
     * @param password
     * @param salt
     * @return
     */
    public static String md5(String password, String salt){
        String hashAlgorithName = "MD5";
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        return simpleHash.toString();
    }
}
