package com.topideal.cn.dubbo.service;

import com.topideal.cn.dubbo.entity.Person;

public interface ISayHelloService {

    String sayHello(String name);

    String sayHello(Person person);
}
