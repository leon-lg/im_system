package com.lld.im.common.enums;

import com.lld.im.common.exception.ApplicationExceptionEnum;

/**
 * @date: 2023/5/16 21:53
 * @author: leon
 */
public enum UserErrorCode implements ApplicationExceptionEnum {
    IMPORT_SIZE_BEYOND(20000,"导入數量超出上限"),
    USER_IS_NOT_EXIST(20001,"用户不存在"),
    MODIFY_USER_ERROR(20003,"更新用户失败"),
    ;

    private int code;
    private String error;
    UserErrorCode(int code, String error){
        this.code = code;
        this.error = error;
    }
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getError() {
        return this.error;
    }
}
