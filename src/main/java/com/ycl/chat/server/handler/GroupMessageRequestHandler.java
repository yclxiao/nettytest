package com.ycl.chat.server.handler;

import com.ycl.chat.protocol.request.GroupMessageRequestPacket;
import com.ycl.chat.protocol.response.GroupMessageResponsePacket;
import com.ycl.chat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 11:33 AM
 * Desc: 类描述
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String toGroupId = groupMessageRequestPacket.getToGroupId();
        String message = groupMessageRequestPacket.getMessage();

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupId(toGroupId);
        groupMessageResponsePacket.setMessage(message);
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(channelHandlerContext.channel()));

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
