package com.likecode.wx;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Zheng Yulai
 * @date : 2017年12月22日
 */
@ConfigurationProperties(prefix = "weixin.config")
@Component
@Data
public class WeiXinConfig {

    private String appId;
    private String appSecret;

}
