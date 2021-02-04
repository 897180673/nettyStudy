package com.study.demo.differerntObjectPipline;

import com.study.demo.encodeAndDecode.EchoHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Main {

    public void startEchoServer(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {

                            ch.pipeline().addLast(new ChannelInBoundHandler1());
                            ch.pipeline().addLast(new ChannelInBoundHandler2());
                            ch.pipeline().addLast(new ChannelInBoundHandler3());
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new ChannelOuntBoundHandler1());
                            ch.pipeline().addLast(new ChannelOuntBoundHandler2());
                            ch.pipeline().addLast(new ChannelOuntBoundHandler3());

                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception {
        new Main().startEchoServer(8088);
    }


}
