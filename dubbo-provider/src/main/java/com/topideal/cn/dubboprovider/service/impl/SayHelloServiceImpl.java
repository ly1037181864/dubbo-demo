package com.topideal.cn.dubboprovider.service.impl;

import com.topideal.cn.dubbo.entity.Person;
import com.topideal.cn.dubbo.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class SayHelloServiceImpl implements ISayHelloService {

    @Override
    public String sayHello(String name) {
        return "hello "+name +"\t 欢迎使用dubbo服务";
    }

    @Override
    public String sayHello(Person person) {
        return "hello "+person.toString() +"\t 欢迎使用dubbo服务";
    }
}
