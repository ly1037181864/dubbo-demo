package com.topideal.cn.dubboconsumer.controller;

import com.topideal.cn.dubbo.entity.Person;
import com.topideal.cn.dubbo.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@RestController
public class SayHelloController {

    @Reference(validation = "true")
    private ISayHelloService service;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name){
        return service.sayHello(name);
    }

    /**
     * 开启参数校验 默认情况下可以配置在客户端也可以配置在服务端，但配置在服务端真实的错误原因不会被显示出来，所以还是配置在客户端
     * @param person
     * @return
     */
    @PostMapping("/sayHello/person")
    public String sayHello2(@RequestBody Person person){
        return service.sayHello(person);
    }
}
