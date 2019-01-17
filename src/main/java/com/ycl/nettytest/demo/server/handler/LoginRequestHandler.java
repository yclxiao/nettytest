package com.ycl.nettytest.demo.server.handler;

import com.ycl.nettytest.demo.protocol.request.LoginRequestPacket;
import com.ycl.nettytest.demo.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 11:18 AM
 * Desc: 类描述
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            System.out.println(new Date() + ": 登陆成功！");
            loginResponsePacket.setSuccess(true);
        } else {
            System.out.println(new Date() + ": 登陆失败！");
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名密码校验失败");
        }
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return "ycl".equals(loginRequestPacket.getUsername()) && "xiao".equals(loginRequestPacket.getPassword());
    }
}
