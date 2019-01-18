package com.ycl.chat.client.handler;

import com.ycl.chat.protocol.response.GroupMessageResponsePacket;
import com.ycl.chat.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 11:37 AM
 * Desc: 类描述
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        String fromGroupId = groupMessageResponsePacket.getFromGroupId();
        Session fromUser = groupMessageResponsePacket.getFromUser();
        String message = groupMessageResponsePacket.getMessage();

        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + message);
    }
}
