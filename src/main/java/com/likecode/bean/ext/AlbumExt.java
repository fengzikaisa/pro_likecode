package com.likecode.bean.ext;

import com.likecode.bean.Album;
import com.likecode.bean.Photo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by wangkai on 2017/10/19.
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AlbumExt extends Album {

    private List<Photo> photos;

}
