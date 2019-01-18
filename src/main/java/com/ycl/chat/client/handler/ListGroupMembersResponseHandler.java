package com.ycl.chat.client.handler;

import com.ycl.chat.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 9:53 AM
 * Desc: 类描述
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        System.out.println("群【" + listGroupMembersResponsePacket.getGroupId() + "】中的人包括：" + listGroupMembersResponsePacket.getSessionList());
    }
}
