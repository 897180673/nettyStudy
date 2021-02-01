package com.study.demo.encodeAndDecode;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class EchoHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("size:"+((ByteBuf) msg).toString(CharsetUtil.UTF_8).length());
        System.out.println("Receive client : [" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8) + "]");
    }

}