package com.likecode.service;

import com.likecode.bean.Album;
import com.likecode.bean.Photo;
import com.likecode.bean.ext.AlbumExt;

import java.util.List;
import java.util.Map;

/**
 * 相册服务
 */
public interface AlbumService {

	/**
	 * 查询所有相片信息
	 * @return
	 */
	Map<AlbumExt,List<Photo>> getAllPhoto();

	/**
	 * 根据相册id查询相册信息
	 * @param id
	 * @return
	 */
	Album getAlbumById(int id);

	/**
	 * 根据相册id查询所属照片
	 * @param albumId
	 * @return
	 */
	List<Photo> getPhotosByAlbumId(int albumId);
}
