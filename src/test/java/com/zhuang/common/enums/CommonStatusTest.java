package com.zhuang.common.enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommonStatusTest {

    @Test
    public void getByValue() {
        System.out.println(CommonStatus.getByValue(-1).getName());
    }
}