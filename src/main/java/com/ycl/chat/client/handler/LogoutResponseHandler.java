package com.ycl.chat.client.handler;

import com.ycl.chat.protocol.response.LogoutResponsePacket;
import com.ycl.chat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:28 PM
 * Desc: 类描述
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if (logoutResponsePacket.isSuccess()) {
            SessionUtil.unBindSession(channelHandlerContext.channel());
        }
    }
}
