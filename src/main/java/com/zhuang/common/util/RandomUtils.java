package com.zhuang.common.util;

import java.util.List;
import java.util.Random;

/**
 * Created by zhuang on 12/30/2017.
 */
public class RandomUtils {

    public static <T> T choice(T[] items) {
        int randomIndex = new Random().nextInt(items.length);
        return items[randomIndex];
    }

    public static <T> T choice(List<T> items) {
        return (T) choice(items.toArray());
    }

}
