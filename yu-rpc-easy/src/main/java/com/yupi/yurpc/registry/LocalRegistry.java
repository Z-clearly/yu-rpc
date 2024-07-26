package com.yupi.yurpc.registry;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 * 使用线程安全的ConcurrentHashMap存储服务注册信息,key为服务名称、value为服务的实现类。之后就可以根据
 * 要调用的服务名称获取到对应的实现类,然后通过反射进行方法调用了。
 */

public class LocalRegistry {


    private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();


    /**
     * 注册服务
     * @param serviceName
     * @param implClass
     */
    public static void register(String serviceName,Class<?> implClass){
        map.put(serviceName,implClass);
    }


    /**
     * 获取服务
     * @param serviceName
     * @return
     */
    public static Class<?> get(String serviceName){
        return map.get(serviceName);
    }


    /**
     * 删除服务
     * @param serviceName
     */
    public static void remove(String serviceName){
        map.remove(serviceName);
    }



}
