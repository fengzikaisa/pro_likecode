package com.likecode.dao;

import com.likecode.bean.Barrage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangkai on 2017/9/21.
 */
@Mapper
public interface BarrageDao {

    public List<Barrage> getBarrages();

    public Barrage insertBarrage(@Param("barrage") Barrage barrage);
}
