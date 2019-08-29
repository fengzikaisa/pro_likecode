package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangkai on 2017/9/21.
 * 弹幕实体
 */
@Alias("barrage")
@Data
public class Barrage implements Serializable {

    private int id;
    private String content;
    private String ip;
    private Date createTime;
    private String isDel;

}
