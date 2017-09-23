package com.likecode.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.likecode.bean.Barrage;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.dao.BarrageDao;
import com.likecode.service.BarrageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.likecode.common.utils.DateUtil.getIntegralEndTime;
import static com.likecode.common.utils.DateUtil.getIntegralStartTime;

/**
 * Created by wangkai on 2017/8/16.
 */
@Service
public class BarrageServiceImpl implements BarrageService {
    @Autowired
    BarrageDao barrageDao;


    @Override
    public ResultBean getBarrages() {
        List<Barrage> list=barrageDao.getBarrages();
        ResultBean bean =new ResultBean();
        if(list!=null && list.size()>0){
            bean.setStatus(ConstantDefinition.SYSTEM_SUCCESS);
        }else{
            bean.setStatus(ConstantDefinition.SYSTEM_RESULT_EMPTY);
        }
        bean.setResult(list);
        return bean;
    }

    @Override
    public ResultBean insertBarrage(@Param("barrage") Barrage barrage) {
        ResultBean bean =new ResultBean();
        //查询ip当天已发送弹幕数量
        Date now=new Date();
        Date startTime=getIntegralStartTime(now);
        Date endTime=getIntegralEndTime(now);
        int count=barrageDao.getBarrageCount(barrage.getIp(),startTime,endTime);
        if(count>10){
            bean.setStatus(ConstantDefinition.SYSTEM_ERROR);
            bean.setResult("今天发送弹幕数量已超过上限，明天再来吧~~~");
            return bean;
        }
        //保存弹幕
        if(barrageDao.insertBarrage(barrage)>0){
            bean.setStatus(ConstantDefinition.SYSTEM_SUCCESS);
        }else{
            bean.setStatus(ConstantDefinition.SYSTEM_ERROR);
        }
        return bean;
    }

}
