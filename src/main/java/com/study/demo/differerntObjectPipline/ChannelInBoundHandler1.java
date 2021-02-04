package com.study.demo.differerntObjectPipline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class  ChannelInBoundHandler1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String  message=((ByteBuf)msg).toString(CharsetUtil.UTF_8);
        System.out.println(message);

        ctx.channel().writeAndFlush("hello ");
    }
}
