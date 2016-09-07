package com.ellepsis.solanteqTest.responseAbstraction;

import java.util.List;

/**
 * Created by Ellepsis on 07.09.2016.
 */
public class ResponseWithCount<T> {
    private List<T> list;
    private long totalCount;

    public ResponseWithCount(List<T> list, long totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
