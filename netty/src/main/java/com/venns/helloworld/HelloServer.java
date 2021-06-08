package com.venns.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author: Venns
 * @create: 2021/6/7 20:43
 **/
public class HelloServer {
    public static void main(String[] args) {
        // 1. 负责组装netty组件, 启动服务器
        new ServerBootstrap()
                // 2. group组
                .group(new NioEventLoopGroup())
                // 3. 选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                // 4. boss 负责处理连接 worker(child) 负责处理读写 ,决定了worker(child) 能执行哪些操作(handler)
                .childHandler(
                        // 5. 代表和客户端进行数据读写的初始化,负责添加别的 handler
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        // 6. 添加具体的 handler
                        nioSocketChannel.pipeline().addLast(new StringDecoder()); // 将bytebuffer 转换为字符串
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){ // 自定义handler
                            // 读事件
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // 打印上一步处理好的字符串
                                System.out.println(msg);
                            }
                        });
                    }
                })
                // 7. 绑定监听端口
                .bind(8080);
    }
}
