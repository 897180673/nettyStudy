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
                                  .addLast(new TestChannelInBoundHandler("SampleInBoundHandlerA", false))
                                  .addLast(new TestChannelInBoundHandler("SampleInBoundHandlerB", false))
                                  .addLast(new TestChannelInBoundHandler("SampleInBoundHandlerC", true));
                          ch.pipeline()
                                  .addLast(new TestChannelOutBoundHandler("SampleOutBoundHandlerA"))
                                  .addLast(new TestChannelOutBoundHandler("SampleOutBoundHandlerB"))
                                  .addLast(new TestChannelOutBoundHandler("SampleOutBoundHandlerC"));


                      }
        }) ;

        //传输的最小的单位是ByteBuf ,headContext如果接受到的msg不是ByteBuf,客户端将收不到消息
        //ctx.writeAndFlush  会从当前的Handler 向headContext 方向传播,如果前面没有OutHandler 那么客户端是收不到的
        //ctx.channel().writeAndFlush 从tailContext 向HeadContext进行传输


        //异常传播总结   在pipline 中异常只会沿着产生异常的Handler ,向着tailContext方向传播 ChannelountBoundHandler
        //的exceptionCaught方法是建议弃用的 ,建议在pipeline的尾部 统一增加一个异常处理的Handler
        ChannelFuture f = serverBootstrap.bind(8088).sync();
        System.out.println("Http Server started， Listening on " + 8088);
        f.channel().closeFuture().sync();

    }
}
