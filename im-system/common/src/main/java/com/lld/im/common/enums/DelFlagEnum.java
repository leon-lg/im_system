package com.lld.im.common.enums;

/**
 * @date: 2023/5/17 16:28
 * @author: leon
 */
public enum DelFlagEnum {

    /**
     * 0 正常；1 删除。
     */
    NORMAL(0),

    DELETE(1),
    ;
    private int code;

    DelFlagEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
