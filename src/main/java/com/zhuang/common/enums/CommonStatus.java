package com.zhuang.common.enums;

import java.util.Arrays;
import java.util.Optional;

public enum  CommonStatus {

    ENABLE(1,"已启用"),
    DISABLE(0,"已禁用"),
    DELETED(-1,"已删除");

    private Integer value;
    private String name;

    CommonStatus(Integer value, String name){
        this.value=value;
        this.name=name;
    }

    public Integer getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static CommonStatus getByValue(Integer value){
         return  Arrays.stream(CommonStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
