package com.zhuang.common.util;

import org.junit.Test;

import java.util.regex.Matcher;

/**
 * Created by zhuang on 12/12/2017.
 */
public class RegexUtilsTest {

    @Test
    public void getPattern()
    {
        Matcher matcher = RegexUtils.getPattern("\\d{2}").matcher("123a45b56");
        System.out.println(matcher.matches());
        while (matcher.find())
        {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void getMatcher()
    {
        Matcher matcher = RegexUtils.getMatcher("\\d{2}","1234,51235,1234,,5,1234");
        System.out.println(matcher.matches());
        while (matcher.find())
        {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void matchHtmlTag()
    {
        Matcher matcher = RegexUtils.getMatcher("(\\<\\w+\\)"
                ,"<div id='div1'>zwb</div>");
        System.out.println(matcher.matches());
        while (matcher.find())
        {
            System.out.println(matcher.group());
        }
    }
}
