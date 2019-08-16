package com.topideal.cn.dubbo.provider.service.impl;

import com.topideal.cn.dubbo.service.ICallbackListerner;
import com.topideal.cn.dubbo.service.ICallbackService;
import org.apache.dubbo.config.annotation.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class CallBackServiceImpl implements ICallbackService {
    private final Map<String,ICallbackListerner> listerners = new ConcurrentHashMap<String,ICallbackListerner>();

    public CallBackServiceImpl(){
        Thread t = new Thread(()->{
            while(true){
                try{
                    for(Map.Entry<String,ICallbackListerner> entry : listerners.entrySet()){
                        try {
                            entry.getValue().changed(getChanged(entry.getKey()));
                        }catch (Exception e){
                            e.printStackTrace();
                            listerners.remove(entry.getKey());
                        }
                    }
                    TimeUnit.SECONDS.sleep(5);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }


    @Override
    public void addListener(String key, ICallbackListerner listerner) {
        System.out.println("key:"+key);
        System.out.println("listerner:"+listerner);

        if (listerner != null) {
            listerners.put(key,listerner);
            listerner.changed(key);
        }
    }

    private String getChanged(String key){
        return "change: "+new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
    }
}
