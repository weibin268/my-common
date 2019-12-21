package com.zhuang.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnowFlakeUtilsTest {

    @Test
    public void nextId() {

        for (int i = 0; i < 100; i++) {
            System.out.println(SnowFlakeUtils.nextId());
        }

    }
}
