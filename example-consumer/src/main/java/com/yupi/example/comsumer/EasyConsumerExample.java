package com.yupi.example.comsumer;

import com.yupi.example.common.model.User;
import com.yupi.example.common.service.UserService;

public class EasyConsumerExample {
    public static void main(String[] args) {
        // TODO: 2024/7/22 需要获取userService的实现类对象
        UserService userService =new UserServiceProxy();
        User user = new User();
        user.setName("yupi");

        //调用
        User newUser= userService.getUser(user);

        if (newUser!=null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("user==null");
        }
    }
}
