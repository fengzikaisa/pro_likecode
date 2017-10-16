package com.likecode.service.impl;

import com.likecode.bean.FriendshipLink;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.dao.FriendshipLinkDao;
import com.likecode.service.FriendshipLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangkai on 2017/10/16.
 */
@Service
public class FriendshipLinkServiceImpl implements FriendshipLinkService {

    @Autowired
    FriendshipLinkDao friendshipLink;

    @Override
    public ResultBean insertFriendshipLink(FriendshipLink vo) {
        if(friendshipLink.insertFriendshipLink(vo)>0){
            return new ResultBean(ConstantDefinition.SYSTEM_SUCCESS,vo,"");
        }
        return new ResultBean(ConstantDefinition.SYSTEM_ERROR,vo,"添加失败");
    }

    @Override
    public List<FriendshipLink> getFriendshipLinkList(String type) {
        return friendshipLink.getFriendshipLinkList(type);
    }
}
