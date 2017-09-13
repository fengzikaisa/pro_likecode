package com.likecode.common.utils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类
 *
 * @author wangkai
 */
@SuppressWarnings("all")
public class PushMessageUtil {
    private final static String appKey = "a3bd12abcce66aa512938391";
    private final static String masterSecret = "695f5c2c3cd1935ba9807321";

    /**
     * 测试方法
     */
    public static void main(String[] args) {
        String alias = "74";
        String alert = "您预约的迪卡侬宣讲会，预计30分钟后开始，请及时进入会场";
        //发送通知
        jSend_notification(alias, alert);

    }

    /**
     * 发送通知
     *
     * @param registrationId 设备标识
     * @param alert          推送内容
     */
    public static String jSend_notification(String alias, String alert) {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        PushPayload payload = send_N(alias, alert);
        String status = null;//返回值 1：推送成功  0：推送失败
        try {
            PushResult result = jpushClient.sendPush(payload);
            if (null != result) {
                System.out.println("服务器返回数据: " + result.toString());
                if (result.isResultOK()) {
                    System.out.println(String.format("发送成功， sendNo= %s,messageId= %s", result.sendno, result.msg_id));
                    status = "1";
                } else {
                    System.out.println("发送失败， 错误代码=" + result.ERROR_MESSAGE_NONE + ", 错误消息=" + result
                            .ERROR_MESSAGE_NONE);
                    status = "0";
                }
            } else {
                System.out.println("无法获取数据");
                status = "0";
            }
        } catch (APIConnectionException e) {
            System.out.println(e);
            status = "0";
        } catch (APIRequestException e) {
            System.out.println(e);
            System.out.println("Error response from JPush server. Should review and fix it. " + e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
            System.out.println("推送失败：+++++++++++++++++++++++++++++" + e.getMsgId());
            status = "0";
        }
        return status;
    }

    public static PushPayload send_N(String alias, String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())//必填    推送平台设置
                .setAudience(Audience.alias(alias))//别名
                .setNotification(Notification.alert(alert))
                /**
                 * 如果目标平台为 iOS 平台 需要在 options
                 * 中通过 apns_production 字段来制定推送环境。
                 * True 表示推送生产环境，False 表示要推送开发环境； 如
                 * 果不指定则为推送生产环境
                 */
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true).setSendno(Integer.parseInt(alias))
                        .build())
                .build();
    }
}