package com.lld.im.service.user.service.impl;

import com.lld.im.common.ResponseVO;
import com.lld.im.common.enums.UserErrorCode;
import com.lld.im.service.user.dao.mapper.ImUserDataMapper;
import com.lld.im.service.user.model.req.ImportUserReq;
import com.lld.im.service.user.service.ImUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2023/5/16 16:16
 * @author: leon
 */
@Service
public class ImUserServiceImpl implements ImUserService {

    @Autowired
    ImUserDataMapper imUserDataMapper;

    @Override
    public ResponseVO importUser(ImportUserReq req) {
        if(req.getUserData().size() > 100){
            ResponseVO.errorResponse(UserErrorCode.IMPORT_SIZE_BEYOND);
        }
        return null;
    }
}
