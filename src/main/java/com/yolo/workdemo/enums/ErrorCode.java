package com.yolo.workdemo.enums;

public enum ErrorCode {
    QUERY_TIME_OUT(1, "查询超时"),
    WAIT_TIME_OUT(2, "等待超时"),
    PREDICT_LIMITED_REFUSED(3, "预估限流，请求拒绝"),
    SYSTEM_ERROR(4, "系统异常"),
    PARAMS_INVALID(5, "参数校验不通过"),
    SQL_CONSTRUCT_ERROR(6, "构造查询SQL失败"),
    PREDICT_CPX_REFUSED(7, "您圈选的标签因数量多或逻辑复杂原因，会导致圈选失败，请简化圈选逻辑后重新提交");

    private int code;
    private String desc;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private ErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ErrorCode getByCode(int code) {
        ErrorCode[] var2 = values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            ErrorCode ec = var2[var4];
            if (code == ec.getCode()) {
                return ec;
            }
        }

        return null;
    }
}
