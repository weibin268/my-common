package com.zhuang.common.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuang on 12/30/2017.
 */
public class RandomUtilsTest {

    @Test
    public void choice() throws Exception {
        List<String> list=new ArrayList<String>();
        list.add("zwb");
        list.add("wei");
        list.add("bin");
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.choice(new String[]{"a","b","c"}));
            System.out.println(RandomUtils.choice(list));
        }
    }

}