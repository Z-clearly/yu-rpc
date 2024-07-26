package com.yupi.example.provider;


import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.registry.LocalRegistry;
import com.yupi.yurpc.server.HttpServer;
import com.yupi.yurpc.server.VertxHttpServer;

/**
 *注意,本地服务注册器和注册中心的作用是有区别的。注册中心的作用侧重于管理注册的服务、提供服务信息给消费
 * 者;而本地服务注册器的作用是根据服务名获取到对应的实现类,是完成调用必不可少的模块。
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);


        //提供服务
        //启动web服务
        HttpServer httpServer =  new VertxHttpServer();
        httpServer.doStart(8080);


    }
}
