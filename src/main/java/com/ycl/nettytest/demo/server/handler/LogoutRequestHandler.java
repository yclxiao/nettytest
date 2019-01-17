package com.ycl.nettytest.demo.server.handler;

import com.ycl.nettytest.demo.protocol.request.LogoutRequestPacket;
import com.ycl.nettytest.demo.protocol.response.LogoutResponsePacket;
import com.ycl.nettytest.demo.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:30 PM
 * Desc: 类描述
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket) throws Exception {
        SessionUtil.unBindSession(channelHandlerContext.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        channelHandlerContext.writeAndFlush(logoutResponsePacket);
    }
}
