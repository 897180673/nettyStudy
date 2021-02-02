package com.study.demo.encodeAndDecode;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

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
                           //   ch.pipeline().addLast(new FixedLengthFrameDecoder(4));//定长
                          //  ByteBuf delimiter = Unpooled.copiedBuffer("&".getBytes());
                         //   ch.pipeline().addLast(new DelimiterBasedFrameDecoder(10, true, false, delimiter)); 特殊字符解码
                            LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder=new LengthFieldBasedFrameDecoder(30,0,1,0,1); //长度域解码
                            ch.pipeline().addLast(lengthFieldBasedFrameDecoder);

                            ch.pipeline().addLast(new EchoHandler());
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
