package com.study.demo.pipelineStudy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TestChannelInBoundHandler extends ChannelInboundHandlerAdapter {
    private final String name;
    private final boolean flush;
    public TestChannelInBoundHandler(String name, boolean flush) {
        this.name = name;
        this.flush = flush;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandler: " + name);
        if (flush) {
            ctx.channel().writeAndFlush(msg);
        } else {
            super.channelRead(ctx, msg);
        }
    }
}
