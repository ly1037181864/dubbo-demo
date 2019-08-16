package com.topideal.cn.dubbo.provider.service.impl;

import com.topideal.cn.dubbo.service.IPayService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class PayServiceImpl implements IPayService {

    @Override
    public String pay(double num) {
        if(num<0)
            Double.parseDouble(String.valueOf(num+"-"));
            //throw new RuntimeException("支付金额不正确");
        System.out.println("已到账\t" + num);
        return "支付成功\t"+num;
    }
}
