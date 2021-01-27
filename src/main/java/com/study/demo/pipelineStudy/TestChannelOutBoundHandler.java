package com.study.demo.pipelineStudy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class TestChannelOutBoundHandler extends ChannelOutboundHandlerAdapter {
    private final String name;
    public TestChannelOutBoundHandler(String name) {
        this.name = name;
    }
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundHandler: " + name);
        super.write(ctx, msg, promise);
    }
}
