package com.zhuang.common.util;

import org.junit.Test;

/**
 * Created by zhuang on 12/10/2017.
 */
public class CmdUtilsTest {

    @Test
    public void exec()
    {
        System.out.println(CmdUtils.exec("cmd /c dir"));
    }

}
