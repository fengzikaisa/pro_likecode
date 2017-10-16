package com.likecode.dao;

import com.likecode.bean.FriendshipLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangkai on 2017/10/16.
 */
@Mapper
public interface FriendshipLinkDao {

    int insertFriendshipLink(FriendshipLink vo);

    List<FriendshipLink> getFriendshipLinkList(String type);
}
