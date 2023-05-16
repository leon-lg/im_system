package com.lld.im.service.user.service;

import com.lld.im.common.ResponseVO;
import com.lld.im.service.user.model.req.ImportUserReq;

/**
 * @date: 2023/5/16 16:16
 * @author: leon
 */
public interface ImUserService {

    ResponseVO importUser(ImportUserReq req);
}
