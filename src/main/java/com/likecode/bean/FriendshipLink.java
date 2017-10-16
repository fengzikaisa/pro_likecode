package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/10/16.
 */
@Alias("friendshipLink")
@Data
public class FriendshipLink implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String url;
    private String name;
    /**
     * 10:可用 20:不可用
     */
    private String idDel;
    /**
     * 1:友链  2:其他
     */
    private String type;
}
