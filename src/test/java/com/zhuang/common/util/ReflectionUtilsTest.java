package com.zhuang.common.util;

import com.zhuang.common.model.ApiResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void getFieldValue() {

        ApiResult myJsonResult = new ApiResult();
        myJsonResult.setMessage("hello zwb!");
        String fieldName = "message";
        if (ReflectionUtils.hasField(myJsonResult.getClass(), fieldName)) {
            System.out.println(ReflectionUtils.getFieldValue(myJsonResult, myJsonResult.getClass(), fieldName));
        } else {
            System.out.println("no such field:" + fieldName);
        }
    }

    @Test
    public void invokeMethod() {
    }
}