package com.crystal.blog.common.util;

import java.util.Date;

/**
 * Created by dingbao on 2018/4/17.
 */
public class SerialNumUtil {

    public static String getSerialNum(){
        String dateStr = new Date().getTime() + "";
        int random = (int) ((Math.random()*900)+100);
        return dateStr + random;
    }
}
