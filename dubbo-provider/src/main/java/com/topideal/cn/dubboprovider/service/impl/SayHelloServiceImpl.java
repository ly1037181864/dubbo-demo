package com.topideal.cn.dubboprovider.service.impl;

import com.topideal.cn.dubbo.entity.Person;
import com.topideal.cn.dubbo.service.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service(timeout = 3000)
public class SayHelloServiceImpl implements ISayHelloService {

    @Override
    public String sayHello(String name) {
        return "hello "+name +"\t 欢迎使用dubbo服务";
    }

    @Override
    public String sayHello(Person person) {
        //上下文信息 可以实现动态参数注入
        RpcContext context = RpcContext.getContext();
        if(context != null){
            System.out.println("url:\t"+context.getUrl());
            Map<String, Object> map = context.get();
            if(map!=null && map.size()>0){
                for(Map.Entry entry : map.entrySet()){
                    System.out.println(entry.getKey()+"\t"+entry.getValue());
                }
            }
        }
        return "hello "+person.toString() +"\t 欢迎使用dubbo服务";
    }

    /**
     * 客户端一步调用 需要注意超时设置，默认不配置的情况下，超时时间为1s
     * @param name
     * @return
     */
    @Override
    public CompletableFuture<String> sayHelloForConsumerAsync(String name) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(sayHello(name));
    }

    /**
     * 服务端异步
     * @param name
     * @return
     */
    @Override
    public CompletableFuture<String> sayHelloForProviderAsync(String name) {
        RpcContext context = RpcContext.getContext();
        return CompletableFuture.supplyAsync(()->{//业务执行已从dubbo线程切换到业务线程，避免了对Dubbo线程池的阻塞
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sayHello(name);
        });
    }
}
