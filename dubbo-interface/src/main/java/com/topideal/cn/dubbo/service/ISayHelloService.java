package com.topideal.cn.dubbo.service;

import com.topideal.cn.dubbo.entity.Person;

import java.util.concurrent.CompletableFuture;

public interface ISayHelloService {

    String sayHello(String name);

    String sayHello(Person person);

    CompletableFuture<String> sayHelloForConsumerAsync(String name);
}
