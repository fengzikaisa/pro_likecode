package com.likecode.dao;

import com.likecode.bean.Barrage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wangkai on 2017/9/21.
 */
@Mapper
public interface BarrageDao {

    List<Barrage> getBarrages();

    int insertBarrage(@Param("barrage") Barrage barrage);

    int getBarrageCount(@Param("ip") String ip,@Param("startTime") Date startTime,@Param("endTime")Date endTime);
}
