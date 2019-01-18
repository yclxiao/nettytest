package com.ycl.chat.server.handler;

import com.ycl.chat.protocol.request.ListGroupMembersRequestPacket;
import com.ycl.chat.protocol.response.ListGroupMembersResponsePacket;
import com.ycl.chat.session.Session;
import com.ycl.chat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 9:38 AM
 * Desc: 类描述
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        List<Session> sessionList = channelGroup.stream().map(SessionUtil::getSession).collect(Collectors.toList());

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setSessionList(sessionList);
        channelHandlerContext.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
