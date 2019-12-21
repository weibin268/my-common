package com.zhuang.common.util;


public class SnowFlakeUtils {

    private static final SnowFlake snowFlake = new SnowFlake(1L, 1L);

    public static long nextId() {
        return snowFlake.nextId();
    }

    public static String nextStrId() {
        return Long.valueOf(snowFlake.nextId()).toString();
    }

}
