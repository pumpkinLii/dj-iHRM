package com.cms.common;

import io.swagger.models.auth.In;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

//HMACSHA256加密签名
public class TokenUtils {
    private static String tokenKey="";
    //对于token的解析 生成
    public static  void setsecret_key(){
        tokenKey= UUID.randomUUID().toString();
        System.out.println("---------------------------------新的密钥为-----------------------------------"+tokenKey);
        //自动切换密钥
    }
    //将加密之后的数组转换为字符串
    public static String parse(byte[] bytes){
        StringBuffer stringBuffer=new StringBuffer();
        String temp;
        for (int i = 0; bytes!=null && i < bytes.length; i++) {
            temp= Integer.toHexString(bytes[i] & 0XFF);
            if (temp.length()==1){
                stringBuffer.append('0');
            }
            stringBuffer.append(temp);
        }

        return stringBuffer.toString().toLowerCase();
    }
    //进行HMACSH256加密
    public static String SH256parse(String value) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac HM256=Mac.getInstance("HmacSHA256");
        SecretKeySpec spec=new SecretKeySpec(tokenKey.getBytes(),"HmacSHA256");
        HM256.init(spec);
        byte[] bytes = HM256.doFinal(value.getBytes());
        String parse = parse(bytes);
        System.out.println("生成的密钥为token为"+parse);
        return parse;

    }
}
