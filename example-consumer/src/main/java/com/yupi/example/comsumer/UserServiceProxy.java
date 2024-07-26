package com.yupi.example.comsumer;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;
import com.yupi.yurpc.model.RpcRequest;
import com.yupi.yurpc.model.RpcResponse;
import com.yupi.yurpc.serializer.JdkSerializer;
import com.yupi.yurpc.serializer.Serializer;

import java.io.IOException;

public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        //指定序列化器
        Serializer serializer = new JdkSerializer();

        //发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();

        try {
            byte[] bodyBytes=serializer.serializer(rpcRequest);
            byte[] result;
            try(HttpResponse httpResponse = HttpRequest.post("localhost:8080")
                    .body(bodyBytes)
                    .execute()){
                result=httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserializer(result,RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
