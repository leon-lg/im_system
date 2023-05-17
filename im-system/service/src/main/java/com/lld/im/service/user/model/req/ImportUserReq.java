package com.lld.im.service.user.model.req;

import com.lld.im.common.model.RequestBase;
import com.lld.im.service.user.dao.ImUserDataEntity;
import lombok.Data;

import java.util.List;

/**
 * @date: 2023/5/16 21:31
 * @author: leon
 * todo:该类中有一个extends RequestBase继承操作还未完成,需要知道该类存在的原因
 */
@Data
public class ImportUserReq extends RequestBase {

    private List<ImUserDataEntity> userData;
}
