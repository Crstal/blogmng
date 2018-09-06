package com.crystal.blog.common.util;

import com.crystal.blog.common.constant.Constant;
import org.thymeleaf.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {

    /**
     * 从项目开始到现在年份列表
     * @return
     */
    public static List<Integer> startToNowYearList() {
        List<Integer> years = new ArrayList<>();
        Integer end = DateUtils.createNow().get(Calendar.YEAR);
        for (int start=Constant.START_YEAR; start<=end; start++) {
            years.add(start);
        }
        return years;
    }
}
