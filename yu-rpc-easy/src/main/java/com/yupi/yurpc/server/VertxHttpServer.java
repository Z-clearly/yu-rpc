package com.yupi.yurpc.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer{

    public void doStart(int port) {
        //创建vert.x实例
        Vertx vertx = Vertx.vertx();

        //创建http服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        server.requestHandler(new HttpServerHandler());

        //启动HTTP服务器并监听指定端口
        server.listen(port,result->{
            if (result.succeeded()){
                System.out.println("Server is now listening on prot"+port);
            }else {
                System.out.println("Failed to start server :"+result.cause());
            }
        });
    }
}
