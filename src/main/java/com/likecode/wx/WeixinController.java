package com.likecode.wx;

import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class WeixinController {

    @Autowired
    private WeiXinApi weixinApi;

    @RequestMapping(value = "/wxConfig", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean getWxConfig(String path) {
        String jsApiTicket = weixinApi.getJsApiTicket();
        Map<String, String> ret = weixinApi.sign(jsApiTicket, path);
        return new ResultBean(ConstantDefinition.SYSTEM_SUCCESS,ret,"成功");
    }
}
