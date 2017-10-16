package com.likecode.service;

import com.likecode.bean.FriendshipLink;
import com.likecode.common.bean.ResultBean;

import java.util.List;

/**
 * Created by wangkai on 2017/10/16.
 */
public interface FriendshipLinkService {

    /**
     * 保存友链
     * @param vo
     * @return
     */
    ResultBean insertFriendshipLink(FriendshipLink vo);

    /**
     * 查询所有友链
     * @param type  10:友链  20:其他
     * @return
     */
    List<FriendshipLink> getFriendshipLinkList(String type);
}
