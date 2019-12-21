package com.zhuang.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuang on 12/12/2017.
 */
public class RegexUtils {

    public static Pattern getPattern(String regex) {
        return Pattern.compile(regex);
    }

    public static Matcher getMatcher(String regex, String input) {
        return getPattern(regex).matcher(input);
    }

}
