package com.lld.im.service.user.service;

import com.lld.im.common.ResponseVO;
import com.lld.im.service.user.dao.ImUserDataEntity;
import com.lld.im.service.user.model.req.DeleteUserReq;
import com.lld.im.service.user.model.req.GetUserInfoReq;
import com.lld.im.service.user.model.req.ImportUserReq;
import com.lld.im.service.user.model.req.ModifyUserInfoReq;
import com.lld.im.service.user.model.resp.GetUserInfoResp;

/**
 * @date: 2023/5/16 16:16
 * @author: leon
 */
public interface ImUserService {

    ResponseVO importUser(ImportUserReq req);

    ResponseVO<GetUserInfoResp> getUserInfo(GetUserInfoReq req);

    ResponseVO<ImUserDataEntity> getSingleUserInfo(String userId , Integer appId);

    ResponseVO deleteUser(DeleteUserReq req);

    ResponseVO modifyUserInfo(ModifyUserInfoReq req);
}
