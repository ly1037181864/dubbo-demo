package com.topideal.cn.dubboconsumer.controller;

import com.topideal.cn.dubbo.entity.Person;
import com.topideal.cn.dubbo.service.ICallbackService;
import com.topideal.cn.dubbo.service.ISayHelloService;
import com.topideal.cn.dubboconsumer.service.impl.CallbackListerner;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class SayHelloController {

    @Reference(validation = "true",timeout = 3000,loadbalance = "roundrobin",
            sticky = true,//自动将请求发送到同一服务器，配置该属性会导致负载策略失效
            mock = "com.topideal.cn.dubboconsumer.service.impl.SayHelloMockImpl",
            stub = "com.topideal.cn.dubboconsumer.service.impl.SayHelloStubImpl")
    private ISayHelloService service;
    @Reference
    private ICallbackService callbackService;

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

    /**
     * 客户端异步调用，注意超时设置 默认是同步调用
     * @param name
     * @return
     */
    @GetMapping("/consumer/async/sayHello/{name}")
    public String sayHelloForCAsync(@PathVariable String name){
        CompletableFuture<String> future = service.sayHelloForConsumerAsync(name);
//        final StringBuilder result = new StringBuilder();
//        future.whenComplete((res,e)->{
//            if(e!=null)
//                result.append(e.getMessage());
//            else
//                result.append(res);
//        });
        String result = null;
        try {
            result = future.get();//阻塞读
        } catch (InterruptedException e) {
            result = e.getMessage();
        } catch (ExecutionException e) {
            result = e.getMessage();
        }
        return result.toString();
    }

    /**
     * 服务端异步调用，注意超时设置 默认是同步调用
     * @param name
     * @return
     */
    @GetMapping("/provider/async/sayHello/{name}")
    public String sayHelloForPAsync(@PathVariable String name){
        CompletableFuture<String> future = service.sayHelloForProviderAsync(name);
        String result = null;
        try {
            result = future.get();//阻塞读
        } catch (InterruptedException e) {
            result = e.getMessage();
        } catch (ExecutionException e) {
            result = e.getMessage();
        }
        return result.toString();
    }

    @GetMapping("provider/callback/{name}")
    public String callBack(@PathVariable String name){
        callbackService.addListener(name, new CallbackListerner());
        return "change事件调用成功";
    }
}
