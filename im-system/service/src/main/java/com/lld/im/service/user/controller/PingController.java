package com.lld.im.service.user.controller;

import com.lld.im.common.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2023/5/17 14:38
 * @author: leon
 */
@RestController
@RequestMapping
public class PingController {

    @RequestMapping("/ping")
    public ResponseVO ping(){
        return ResponseVO.successResponse();
    }
}
