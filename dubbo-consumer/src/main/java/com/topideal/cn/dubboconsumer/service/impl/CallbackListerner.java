package com.topideal.cn.dubboconsumer.service.impl;

import com.topideal.cn.dubbo.service.ICallbackListerner;

import java.io.Serializable;

public class CallbackListerner implements ICallbackListerner, Serializable {

    @Override
    public void changed(String msg) {
        System.out.println("callback事件" + msg);
    }
}
