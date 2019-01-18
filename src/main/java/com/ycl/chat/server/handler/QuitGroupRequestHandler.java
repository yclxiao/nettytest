package com.ycl.chat.server.handler;

import com.ycl.chat.protocol.request.QuitGroupRequestPacket;
import com.ycl.chat.protocol.response.QuitGroupResponsePacket;
import com.ycl.chat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 10:54 AM
 * Desc: 类描述
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(channelHandlerContext.channel());

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(groupId);
        quitGroupResponsePacket.setSuccess(true);

        channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
