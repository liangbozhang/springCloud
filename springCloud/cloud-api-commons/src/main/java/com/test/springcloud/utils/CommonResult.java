package com.test.springcloud.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String msg;
    private T data;
    private String ext;

    public CommonResult(Integer code, String msg){
        this(code, msg, null, null);
    }

    public CommonResult<T> ok(T data) {
        this.code = 200;
        this.msg = null;
        this.data = data;
        return this;
    }

    public CommonResult<T> ok(String msg, T data) {
        this.code = 200;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public CommonResult<T> error(T data) {
        this.code = 400;
        this.msg = null;
        this.data = data;
        return this;
    }

    public CommonResult<T> error(String msg, T data) {
        this.code = 400;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public CommonResult<T> error(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

}
