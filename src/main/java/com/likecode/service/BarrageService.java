package com.likecode.service;

import com.likecode.bean.Barrage;
import com.likecode.common.bean.ResultBean;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wangkai on 2017/8/16.
 */
public interface BarrageService {

    /**
     * 查询所有弹幕
     * @return
     */
    public ResultBean getBarrages();

    /**
     * 保存弹幕
     * @param barrage
     */
    public ResultBean insertBarrage(@Param("barrage") Barrage barrage);
}
