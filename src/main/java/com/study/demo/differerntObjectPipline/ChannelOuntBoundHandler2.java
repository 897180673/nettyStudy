package com.study.demo.differerntObjectPipline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ChannelOuntBoundHandler2   extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        msg += "outbound2 ";

        ctx.writeAndFlush(msg);
    }
}
