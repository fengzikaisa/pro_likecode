package com.likecode.controller;

import com.likecode.bean.FriendshipLink;
import com.likecode.bean.ext.UserExt;
import com.likecode.common.SystemHelper;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.controller.BaseController;
import com.likecode.service.FriendshipLinkService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by wangkai on 2017/9/25.
 * 友链控制器
 */
@Log4j
@Controller
@RequestMapping("friendshipLink")
public class FriendshipLinkController extends BaseController {

    @Autowired
    FriendshipLinkService friendshipLinkService;

    /**
     * 添加页面
     * @param model
     * @return
     */
    @RequestMapping(value="addPage")
    public String addPage(Model model,HttpSession session) {
        if(SystemHelper.isLogin()){
            return "friendshipLink/addPage";
        }
        return "redirect:/blog";

    }

    /**
     * 添加
     * @param model
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="add",method = RequestMethod.POST)
    public ResultBean add(Model model, FriendshipLink vo, HttpSession session) {
        Integer userId=SystemHelper.currUserId();
        if(userId!=null && userId==1){
            return friendshipLinkService.insertFriendshipLink(vo);
        }else{
            return new ResultBean();
        }
    }
}
