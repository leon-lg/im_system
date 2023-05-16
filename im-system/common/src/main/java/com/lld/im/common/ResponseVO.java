package com.lld.im.common;

import com.lld.im.common.exception.ApplicationExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2023/5/16 21:36
 * @author: leon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> {
    private int code;
    private String msg;
    private T data;

    public ResponseVO(int code, String msg){
        this.code = code;
        this.msg =  msg;
    }

    public static ResponseVO successResponse(Object data){
        return new ResponseVO(200, "success", data);
    }

    public static ResponseVO successResponse(){
        return new ResponseVO(200, "success");
    }

    public static ResponseVO errorResponse(){
        return new ResponseVO(500, "系统内部异常");
    }

    public static ResponseVO errorResponse(ApplicationExceptionEnum error){
        return new ResponseVO(error.getCode(), error.getError());
    }
}
