package com.crystal.blog.common.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 读取字符串第i次出现特定符号的位置
     * @param string
     * @param i
     * @return
     */
    public static int getCharacterPosition(String string, int i, String character) {
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile(character).matcher(string);
        int mIdx = 0;
        if (slashMatcher.matches()) {
            while (slashMatcher.find()) {
                mIdx++;
                //当"/"符号第三次出现的位置
                if (mIdx == i) {
                    break;
                }
            }
            return slashMatcher.start();
        }

        return -1;
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i = 0 ; i < length; ++i){
            int number = random.nextInt(62);//[0,62)

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 判断是否为正确的邮件格式
     * @param str
     * @return boolean
     */
    public static boolean isEmail(String str){
        if(str == null || "".equals(str)){
            return false;
        }
        return str.matches("^[\\w-]+@[\\w-]+(\\.[\\w-]+)+$");
    }

    /**
     * 判断是否为正确的手机格式
     * @param str
     * @return boolean
     */
    public static boolean isMobile(String str){
        if(str == null || "".equals(str)){
            return false;
        }
        return str.matches("^1((3[0-9])|(4[5|7])|(5([0-3]|[5-9]))|(8[0,5-9]))\\d{8}$");

    }
}
