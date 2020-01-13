package com.kiger.schoolmap.bean;


import java.util.List;

public class DealResult {

    private boolean isSucceed;
    private String resultInfo;
    private Object data;

    public DealResult() {}

    public DealResult(boolean isSucceed, String resultInfo) {
        this.isSucceed = isSucceed;
        this.resultInfo = resultInfo;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
