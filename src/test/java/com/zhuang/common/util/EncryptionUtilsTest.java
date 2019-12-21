package com.zhuang.common.util;

import org.junit.Test;

/**
 * Created by zhuang on 9/2/2017.
 */
public class EncryptionUtilsTest {

    @Test
    public void encryptByMD5()
    {
        String result = EncryptionUtils.encryptByMD5("庄伟斌");
        System.out.println(result);
    }

    @Test
    public void encryptByAES()
    {
        String result = EncryptionUtils.encryptByAES("庄伟斌","zwb");
        System.out.println(result);
    }

    @Test
    public void decryptByAES()
    {
        String result = EncryptionUtils.decryptByAES("LCrp/3rgxC8l2hrUpJq56w==","zwb");
        System.out.println(result);
    }
}
