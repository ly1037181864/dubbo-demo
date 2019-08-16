package com.topideal.cn.dubboconsumer.service.impl;

import com.topideal.cn.dubbo.entity.Person;
import com.topideal.cn.dubbo.service.ISayHelloService;

import java.util.concurrent.CompletableFuture;

/**
 * 服务容灾或者降级的处理方式 配置在客户端
 */
public class SayHelloMockImpl implements ISayHelloService {

    @Override
    public String sayHello(String name) {
        return "hello "+name +"服务刚刚开了一个小差";
    }

    @Override
    public String sayHello(Person person) {
        return "hello "+person.toString() +"服务刚刚开了一个小差";
    }

    @Override
    public CompletableFuture<String> sayHelloForConsumerAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

    @Override
    public CompletableFuture<String> sayHelloForProviderAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
