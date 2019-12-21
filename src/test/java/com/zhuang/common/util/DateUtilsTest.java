package com.zhuang.common.util;

import org.junit.Test;

import java.util.Date;

/**
 * Created by zhuang on 12/30/2017.
 */
public class DateUtilsTest {

    @Test
    public void parseDate() {
        System.out.println(DateUtils.parseDate("2017-12-30 00:00:00"));
    }

    @Test
    public void parseTimeMillis() {
        System.out.println(DateUtils.parseTimeMillis("2018-01-01 00:00:00"));
    }

    @Test
    public void parseString() {
        System.out.println(DateUtils.parseString(new Date()));
    }

}