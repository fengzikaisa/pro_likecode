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

import java.util.List;

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
        if(barrageDao.insertBarrage(barrage).getId()>0){
            bean.setStatus(ConstantDefinition.SYSTEM_SUCCESS);
        }else{
            bean.setStatus(ConstantDefinition.SYSTEM_ERROR);
        }
        return bean;
    }
}
