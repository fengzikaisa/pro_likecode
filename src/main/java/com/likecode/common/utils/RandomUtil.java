package com.likecode.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {
    //公共头
    private static String commonName = "likecode";

    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    public static int getRandomForLol(int min,int max){
        Random rand = new Random();
        // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
        int randNumber =rand.nextInt(max - min + 1) + min;
        return randNumber;
    }

    /**
     * 随机生成名字后六位
     * @return
     */
    public static String randomName(){
        int random = (int)((Math.random()*9+1)*100000);
        return commonName + random;
    }

    //订单号的生成规则
    public static String orderNo(Integer courseId,Integer userId){
        int totalLength = 20;
        String date = currentDate();
        String orderNo = date + courseId + userId;
        int length  = orderNo.length();
        if(totalLength > length ){
            int randomLength = totalLength - length;
            int random = getRandomNumber(randomLength);
            orderNo += random;
        }
        return orderNo;
    }

    /**
     * 获取当前日期yyyymmdd
     * @return
     */
    public static String currentDate(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(d);
    }

    /**
     * 得到n位长度的随机数
     * @param n 随机数的长度
     * @return 返回  n位的随机整数
     */
    public static int getRandomNumber(int n){
        int temp = 0;
        int min = (int) Math.pow(10, n-1);
        int max = (int) Math.pow(10, n);
        Random rand = new Random();
        while(true){
            temp = rand.nextInt(max);
            if(temp >= min)
                break;
        }

        return temp;
    }

}
