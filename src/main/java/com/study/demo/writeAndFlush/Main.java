package com.study.demo.writeAndFlush;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;


public class Main {

    public static void main(String[] args) throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup(1);

        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FixedLengthFrameDecoder(10));
                        ch.pipeline().addLast(new ResponseSampleEncoder())  ;
                        ch.pipeline().addLast(new RequestSampleHandler());

                    }
                });

        ChannelFuture f = serverBootstrap.bind(8088).sync();
        System.out.println("Http Server started， Listening on " + 8088);
        f.channel().closeFuture().sync();
    }
}
