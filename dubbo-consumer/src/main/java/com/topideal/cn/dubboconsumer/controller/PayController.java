package com.topideal.cn.dubboconsumer.controller;

import com.topideal.cn.dubbo.service.IPayService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

    @Reference(mock = "return '调用接口失败，服务不可用'")//主要是体现在服务不可用或者服务不稳定时的一种处理方式，当服务不可用或者服务不稳定时，直接调用代理类或指定返回默认值
    private IPayService service;

    @GetMapping("/pay/{num}")
    public String pay(@PathVariable double num){
        return service.pay(num);
    }
}
