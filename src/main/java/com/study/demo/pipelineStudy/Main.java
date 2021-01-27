package com.study.demo.pipelineStudy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;

import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Main {

    public static void main(String[] args) throws Exception {
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();

        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                  .childHandler(new ChannelInitializer< SocketChannel>() {
                      @Override
                      protected void initChannel( SocketChannel ch ) throws Exception {
                          ch.pipeline()
                                  .addLast(new TestChannelOutBoundHandler("SampleOutBoundHandlerD"))
                                  .addLast(new TestChannelInBoundHandler("SampleInBoundHandlerA", false))
                                  .addLast(new TestChannelInBoundHandler("SampleInBoundHandlerB", false))
                                  .addLast(new TestChannelInBoundHandler("SampleInBoundHandlerC", true));
                          ch.pipeline()
                                  .addLast(new TestChannelOutBoundHandler("SampleOutBoundHandlerA"))
                                  .addLast(new TestChannelOutBoundHandler("SampleOutBoundHandlerB"))
                                  .addLast(new TestChannelOutBoundHandler("SampleOutBoundHandlerC"));
                      }
        }) ;


        ChannelFuture f = serverBootstrap.bind(8088).sync();
        System.out.println("Http Server started， Listening on " + 8088);
        f.channel().closeFuture().sync();

    }
}
