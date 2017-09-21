package com.likecode.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by wangkai on 2017/9/21.
 */
@Alias("barrage")
@Data
public class Barrage implements Serializable {

    private int id;
    private String content;

}
