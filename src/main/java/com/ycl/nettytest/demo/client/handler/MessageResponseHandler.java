package com.ycl.nettytest.demo.client.handler;

import com.ycl.nettytest.demo.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:54 AM
 * Desc: 类描述
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
//        System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());

        String message = messageResponsePacket.getMessage();
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();

        System.out.println(fromUserId + ":" + fromUserName + " -> " + message);
    }
}
