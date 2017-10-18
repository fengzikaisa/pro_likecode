package com.likecode.service.impl;

import com.likecode.bean.Album;
import com.likecode.bean.TestBean;
import com.likecode.common.bean.ParameterBean;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.dao.AlbumDao;
import com.likecode.dao.TestDao;
import com.likecode.service.AlbumService;
import com.likecode.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	AlbumDao albumDao;

	@Override
	public List<Album> getAlbumByType(String type) {
		return albumDao.getAlbumByType(type);
	}
}
