package com.zhuang.common.model.args;

import java.util.List;

/**
 * Created by zhuang on 8/19/2017.
 */
public class IdAndIdListArgs extends IdArgs {

    private List<String> idList;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}
