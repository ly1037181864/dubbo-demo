package com.topideal.cn.dubboconsumer.service.impl;

import com.topideal.cn.dubbo.service.IPayService;

public class PayServiceMock implements IPayService {
    private IPayService payService;

    public PayServiceMock(IPayService payService) {
        this.payService = payService;
    }

    @Override
    public String pay(double num) {
        return "刚刚网络开了个小差";
    }
}
