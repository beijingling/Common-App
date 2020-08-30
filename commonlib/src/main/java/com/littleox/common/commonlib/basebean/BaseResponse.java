package com.littleox.common.commonlib.basebean;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private T data;
    private String code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean success() {
        return "0000".equals(code);
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
