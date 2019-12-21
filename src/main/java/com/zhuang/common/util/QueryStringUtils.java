package com.zhuang.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QueryStringUtils {

    public static String toString(Map<String, String> param) {
        String result;
        List<String> lsParam = new ArrayList<String>();
        for (Entry<String, String> entry : param.entrySet()) {
            lsParam.add(entry.getKey() + "=" + entry.getValue());
        }
        result = StringUtils.join(lsParam, "&");
        return result;
    }

    public static Map<String, Object> toMap(String param) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (param == null || param == "") {
            return result;
        }
        String[] arrParam = param.split("&");
        for (String strParam : arrParam) {
            String[] arrP = strParam.split("=");
            result.put(arrP[0], arrP.length > 1 ? arrP[1] : "");
        }
        return result;
    }

}
