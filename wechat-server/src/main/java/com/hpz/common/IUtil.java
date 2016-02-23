package com.hpz.common;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy on 15/5/7.
 */
public class IUtil {

    public static List<String> singleStringList(String msg){
        List<String> list = new ArrayList<String>();
        list.add(msg);
        return list;
    }
    /***
     * md5加密算法
     * @param text
     * @return
     */
    public static String md5entype(String text){
        byte[] bytes = text.getBytes();
        char hd[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            MessageDigest md5In = MessageDigest.getInstance("MD5");
            md5In.update(bytes); byte[] md = md5In.digest();
            int j = md.length;char str[] = new char[j * 2];int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hd[byte0 >>> 4 & 0xf]; str[k++] = hd[byte0 & 0xf];
            } return new String(str);
        } catch (Exception e) {
            e.printStackTrace(); return null;
        }
    }


    /**
     * 产生一个六位数的验证码  手机验证码
     * @return
     */
    public static int generateRegisterCode(){
        int x=(int)(Math.random()*1000000);
        if(x<100000){
            x = generateRegisterCode(); //主要为了防止最后一个数字是0再生成一次
        }
        return x;
    }


    /**
     * 产生一个六位数的验证码
     * @return
     */
    public static String generatEmailCode(){
        String x=String.valueOf(IUtil.generateRegisterCode());
        String y=String.valueOf(IUtil.generateRegisterCode());
        return IUtil.md5entype(x + y);
    }


    public static  String getInviteCode() {
        char[] r=new char[]{'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
                'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'm', 'n', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x'};
        int binLen=r.length;
        char[] str = new char[8];
        for(int i=0; i<8; i++) {
            int x=(int)(Math.random()*binLen);
            str[i] = r[x];
        }
        return String.valueOf(str);
    }
}
