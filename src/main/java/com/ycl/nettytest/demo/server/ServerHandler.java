package com.ycl.nettytest.demo.server;

import com.ycl.nettytest.demo.protocol.Packet;
import com.ycl.nettytest.demo.protocol.PacketCodeC;
import com.ycl.nettytest.demo.protocol.request.LoginRequestPacket;
import com.ycl.nettytest.demo.protocol.request.MessageRequestPacket;
import com.ycl.nettytest.demo.protocol.response.LoginResponsePacket;
import com.ycl.nettytest.demo.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 3:51 PM
 * Desc: 类描述
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(new Date() + ": 客户端开始登陆...");
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

            if (valid(loginRequestPacket)) {
                System.out.println(new Date() + ": 登陆成功！");
                loginResponsePacket.setSuccess(true);
            } else {
                System.out.println(new Date() + ": 登陆失败！");
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名密码校验失败");
            }

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return "ycl".equals(loginRequestPacket.getUsername()) && "xiao".equals(loginRequestPacket.getPassword());
    }

}
