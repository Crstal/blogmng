package com.crystal.blog.common.util;

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
}
