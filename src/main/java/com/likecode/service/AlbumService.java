package com.likecode.service;

import com.likecode.bean.Album;
import java.util.List;

/**
 * 相册服务
 */
public interface AlbumService {

	/**
	 * 根据类型查询相册
	 * @param type 10:保密  20:其他
	 * @return
	 */
	List<Album> getAlbumByType(String type);
}
