package com.lld.im.service.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lld.im.common.ResponseVO;
import com.lld.im.common.enums.DelFlagEnum;
import com.lld.im.common.enums.UserErrorCode;
import com.lld.im.common.exception.ApplicationException;
import com.lld.im.service.user.dao.ImUserDataEntity;
import com.lld.im.service.user.dao.mapper.ImUserDataMapper;
import com.lld.im.service.user.model.req.DeleteUserReq;
import com.lld.im.service.user.model.req.GetUserInfoReq;
import com.lld.im.service.user.model.req.ImportUserReq;
import com.lld.im.service.user.model.req.ModifyUserInfoReq;
import com.lld.im.service.user.model.resp.GetUserInfoResp;
import com.lld.im.service.user.model.resp.ImportUserResp;
import com.lld.im.service.user.service.ImUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            return ResponseVO.errorResponse(UserErrorCode.IMPORT_SIZE_BEYOND);
        }

        List<String> successId = new ArrayList<>();
        List<String> errorId = new ArrayList<>();
        ImportUserResp resp = new ImportUserResp();

        for(ImUserDataEntity data : req.getUserData()){
            try{
                data.setAppId(req.getAppId());
                int insert = imUserDataMapper.insert(data);
                if(insert == 1){
                    successId.add(data.getUserId());
                }
            }catch (Exception e){
                e.printStackTrace();
                errorId.add(data.getUserId());
            }
        }
        resp.setSuccessId(successId);
        resp.setErrorId(errorId);

        return ResponseVO.successResponse(resp);
    }

    @Override
    public ResponseVO<GetUserInfoResp> getUserInfo(GetUserInfoReq req) {
        QueryWrapper<ImUserDataEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", req.getAppId());
        wrapper.in("user_id", req.getUserIds());
        wrapper.eq("del_flag", DelFlagEnum.NORMAL.getCode());

        List<ImUserDataEntity> userDataEntities = imUserDataMapper.selectList(wrapper);

        Set<String> set = new HashSet<>();
        List<String> failUser = new ArrayList<>();

        for(ImUserDataEntity data : userDataEntities){
            set.add(data.getUserId());
        }

        for(String userId : req.getUserIds()){
            if(!set.contains(userId)){
                failUser.add(userId);
            }
        }

        GetUserInfoResp getUserInfoResp = new GetUserInfoResp();
        getUserInfoResp.setUserDataItem(userDataEntities);
        getUserInfoResp.setFailUser(failUser);
        return ResponseVO.successResponse(getUserInfoResp);
    }

    @Override
    public ResponseVO<ImUserDataEntity> getSingleUserInfo(String userId, Integer appId) {
        QueryWrapper<ImUserDataEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("app_id", appId);
        ImUserDataEntity imUserDataEntity = imUserDataMapper.selectOne(wrapper);

        if(imUserDataEntity == null){
            return ResponseVO.errorResponse(UserErrorCode.USER_IS_NOT_EXIST);
        }

        return ResponseVO.successResponse(imUserDataEntity);
    }

    @Override
    public ResponseVO<ImportUserResp> deleteUser(DeleteUserReq req) {
        ImUserDataEntity entity = new ImUserDataEntity();
        entity.setDelFlag(DelFlagEnum.DELETE.getCode());

        List<String> successId = new ArrayList<>();
        List<String> errorId = new ArrayList<>();

        for(String userId : req.getUserId()){
            QueryWrapper<ImUserDataEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            wrapper.eq("app_id", req.getAppId());
            wrapper.eq("del_flag", DelFlagEnum.NORMAL.getCode());
            int update = 0;

            try{
                update = imUserDataMapper.update(entity, wrapper);
                if(update > 0){
                    successId.add(userId);
                }else{
                    errorId.add(userId);
                }
            }catch(Exception e){
                e.printStackTrace();
                errorId.add(userId);
            }
        }

        ImportUserResp resp = new ImportUserResp();
        resp.setErrorId(errorId);
        resp.setSuccessId(successId);

        return ResponseVO.successResponse(resp);
    }

    @Override
    public ResponseVO modifyUserInfo(ModifyUserInfoReq req) {
        QueryWrapper<ImUserDataEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", req.getUserId());
        wrapper.eq("app_id", req.getAppId());
        wrapper.eq("del_flag", DelFlagEnum.NORMAL.getCode());
        ImUserDataEntity user = imUserDataMapper.selectOne(wrapper);
        if(user == null){
            throw new ApplicationException(UserErrorCode.USER_IS_NOT_EXIST);
        }

        ImUserDataEntity update = new ImUserDataEntity();
        BeanUtils.copyProperties(req, update);

//        update.setAppId(null);
//        update.setUserId(null);
        int update1 = imUserDataMapper.update(update, wrapper);
        if(update1 == 1){
            return ResponseVO.successResponse();
        }
        throw new ApplicationException(UserErrorCode.MODIFY_USER_ERROR);
    }


}
