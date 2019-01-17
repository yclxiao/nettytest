package com.ycl.nettytest.demo.server.handler;

import com.ycl.nettytest.demo.protocol.request.LoginRequestPacket;
import com.ycl.nettytest.demo.protocol.response.LoginResponsePacket;
import com.ycl.nettytest.demo.session.Session;
import com.ycl.nettytest.demo.util.LoginUtil;
import com.ycl.nettytest.demo.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

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
        loginResponsePacket.setUserName(loginRequestPacket.getUsername());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
//            LoginUtil.markAsLogin(channelHandlerContext.channel());

            System.out.println("[" + loginRequestPacket.getUsername() + "]登录成功");

            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), channelHandlerContext.channel());

        } else {
            System.out.println(new Date() + ": 登陆失败！");
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名密码校验失败");
        }

        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return "pwd".equals(loginRequestPacket.getPassword());
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }

}
