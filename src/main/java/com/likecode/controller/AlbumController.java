/*
package com.likecode.controller;

import com.likecode.bean.Album;
import com.likecode.bean.Photo;
import com.likecode.bean.ext.AlbumExt;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.controller.BaseController;
import com.likecode.service.AlbumService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

*/
/**
 * Created by wangkai on 2017/10/19.
 * 相册控制器
 *//*

@Log4j
@Controller
@RequestMapping("album")
public class AlbumController extends BaseController {

    @Autowired
    AlbumService albumService;


    */
/**
     * 我的图片
     * @param model
     * @return
     *//*

    @RequestMapping("myPicture")
    public String myPicture(Model model) {

        Map<AlbumExt, List<Photo>> map=albumService.getAllPhoto();
        List<AlbumExt> albums=new ArrayList<AlbumExt>();
        Set<AlbumExt> albumsSet=map.keySet();
        for(AlbumExt albumExt:albumsSet){
            List<Photo> photos=map.get(albumExt);
            albumExt.setPhotos(photos);
            albums.add(albumExt);
        }
        model.addAttribute("albums",albums);
        return "blog/myPicture";
    }

    */
/**
     * 加密相册
     * @param model
     * @param pwd
     * @param session
     * @return
     *//*

    @ResponseBody
    @RequestMapping(value="secrecyPicture",method = RequestMethod.POST)
    public ResultBean secrecyPicture(Model model, String pwd, HttpSession session,int albumId) {

        log.info("pwd:"+pwd+"   albumId:"+albumId);
        Album album=albumService.getAlbumById(albumId);

        if(StringUtils.isNotBlank(album.getPwd()) && album.getPwd().equals(pwd)){
            List<Photo> photos=albumService.getPhotosByAlbumId(albumId);
            return new ResultBean("10000",photos,"请求成功");
        }else{
            return new ResultBean("10001",null,"请求失败");
        }
    }

}
*/
