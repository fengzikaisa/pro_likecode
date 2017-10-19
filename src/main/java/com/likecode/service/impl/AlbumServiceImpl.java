package com.likecode.service.impl;

import com.likecode.bean.Album;
import com.likecode.bean.Photo;
import com.likecode.bean.TestBean;
import com.likecode.bean.ext.AlbumExt;
import com.likecode.common.bean.ParameterBean;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.dao.AlbumDao;
import com.likecode.dao.TestDao;
import com.likecode.service.AlbumService;
import com.likecode.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	AlbumDao albumDao;


	@Override
	public Map<AlbumExt, List<Photo>> getAllPhoto() {
		List<Map<String, Object>> rows=albumDao.getAlbumsAndPhoto();
		Map<AlbumExt, List<Photo>> map=new LinkedHashMap<AlbumExt, List<Photo>>();
		for(Map<String, Object> row:rows){
			AlbumExt albumExt=new AlbumExt();
			Photo photo=new Photo();
			//相册
			int albumId=(Integer) row.get("albumId");
			String albumName=String.valueOf(row.get("albumName"));
			String type=String.valueOf(row.get("type"));
			albumExt.setId(albumId);
			albumExt.setAlbumName(albumName);
			albumExt.setType(type);

			//相片
			int photoId=(Integer)row.get("photoId");
			String photoName=String.valueOf(row.get("photoName"));
			String url=String.valueOf(row.get("url"));
			photo.setId(photoId);
			photo.setPhotoName(photoName);
			photo.setUrl(url);

			List<Photo> photos=map.get(albumExt);
			if(photos==null){
				photos=new ArrayList<Photo>();
				photos.add(photo);
				map.put(albumExt,photos);
			}else{
				photos.add(photo);
			}
		}
		return map;
	}

	@Override
	public Album getAlbumById(int id) {
		return albumDao.getAlbumById(id);
	}

	@Override
	public List<Photo> getPhotosByAlbumId(int albumId) {
		return albumDao.getPhotosByAlbumId(albumId);
	}
}
