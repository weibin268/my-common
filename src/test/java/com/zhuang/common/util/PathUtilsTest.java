package com.zhuang.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathUtilsTest {

    @Test
    public void getPathByPackage() {

        System.out.println(PathUtils.getPathByPackage("com.my.upms"));
    }

    @Test
    public void combine() {
        System.out.println(PathUtils.combine("a\\a", "/b\\b", "c\\c"));
    }

    @Test
    public void getAbsolutePath() {
        System.out.println(PathUtils.getAbsolutePath("./com"));
    }
}