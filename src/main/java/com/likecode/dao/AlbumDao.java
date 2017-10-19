package com.likecode.dao;

import com.likecode.bean.Album;
import com.likecode.bean.Photo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * Created by wangkai on 2017/9/21.
 */
@Mapper
public interface AlbumDao {

    /**
     * 查询所有相片及相册信息
     * @return
     */
    List<Map<String, Object>> getAlbumsAndPhoto();

    Album getAlbumById(int id);

    List<Photo> getPhotosByAlbumId(int albumId);

}
