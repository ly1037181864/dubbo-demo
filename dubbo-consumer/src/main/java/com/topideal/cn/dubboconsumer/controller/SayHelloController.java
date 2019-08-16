package com.topideal.cn.dubboconsumer.controller;

import com.topideal.cn.dubbo.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

    @Reference
    private ISayHelloService service;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name){
        return service.sayHello(name);
    }
}
