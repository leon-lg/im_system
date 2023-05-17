package com.lld.im.common.model;

import lombok.Data;

/**
 * @description:
 * @author: lld
 * @version: 1.0
 * todo:这部分的字段到底是用来干什么的，后续了解一下
 */
@Data
public class RequestBase {
    private Integer appId;

    private String operater;

    private Integer clientType;

    private String imei;
}
