package com.likecode.dao;

import com.likecode.bean.Album;
import com.likecode.bean.Barrage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wangkai on 2017/9/21.
 */
@Mapper
public interface AlbumDao {

    List<Album> getAlbumByType(String type);

}
