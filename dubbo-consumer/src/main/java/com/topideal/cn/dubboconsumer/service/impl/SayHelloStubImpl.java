package com.topideal.cn.dubboconsumer.service.impl;

import com.topideal.cn.dubbo.entity.Person;
import com.topideal.cn.dubbo.service.ISayHelloService;

import java.util.concurrent.CompletableFuture;

/**
 * 主要是针对服务参数校验，主要是在客户端就先期进行参数校验，如果参数不合法，则不调用服务端代码
 */
public class SayHelloStubImpl implements ISayHelloService {

    private ISayHelloService sayHelloService;

    public SayHelloStubImpl(ISayHelloService sayHelloService) {
        this.sayHelloService = sayHelloService;
    }

    @Override
    public String sayHello(String name) {
        if(name == null)
            return "参数校验不正确";
        return sayHelloService.sayHello(name);
    }

    @Override
    public String sayHello(Person person) {
        if(person == null)
            return "参数校验不正确";
        else if(person.getName() == null || "".equals(person.getName()))
            return "参数校验不正确";
        return sayHelloService.sayHello(person);
    }

    @Override
    public CompletableFuture<String> sayHelloForConsumerAsync(String name) {
        if(name == null)
            return CompletableFuture.completedFuture("参数校验不正确");
        return sayHelloService.sayHelloForConsumerAsync(name);
    }

    @Override
    public CompletableFuture<String> sayHelloForProviderAsync(String name) {
        if(name == null)
            return CompletableFuture.completedFuture("参数校验不正确");
        else
            return sayHelloService.sayHelloForProviderAsync(name);
    }
}
