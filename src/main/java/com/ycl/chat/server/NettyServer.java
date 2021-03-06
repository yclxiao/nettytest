package com.ycl.chat.server;

import com.ycl.chat.codec.PacketDecoder;
import com.ycl.chat.codec.PacketEncoder;
import com.ycl.chat.codec.Spliter;
import com.ycl.chat.handler.IMIdleStateHandler;
import com.ycl.chat.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 2:19 PM
 * Desc: 类描述
 */
public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                .childAttr(clientKey, "clientValue")
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new IMIdleStateHandler());
                        nioSocketChannel.pipeline().addLast(new Spliter());
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(AuthHandler.INSTANCE);

                        nioSocketChannel.pipeline().addLast(IMHandler.INSTANCE);//平行的handler合并到一处
//                        nioSocketChannel.pipeline().addLast(MessageRequestHandler.INSTANCE);
//                        nioSocketChannel.pipeline().addLast(CreateGroupRequestHandler.INSTANCE);
//                        nioSocketChannel.pipeline().addLast(ListGroupMembersRequestHandler.INSTANCE);
//                        nioSocketChannel.pipeline().addLast(JoinGroupRequestHandler.INSTANCE);
//                        nioSocketChannel.pipeline().addLast(QuitGroupRequestHandler.INSTANCE);
//                        nioSocketChannel.pipeline().addLast(GroupMessageRequestHandler.INSTANCE);
//                        nioSocketChannel.pipeline().addLast(LogoutRequestHandler.INSTANCE);

                        nioSocketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        bindPort(serverBootstrap, BEGIN_PORT);

    }

    private static void bindPort(ServerBootstrap serverBootstrap, int beginPort) {
        serverBootstrap.bind(beginPort).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + beginPort + "]绑定成功！");
            } else {
                System.out.println("端口[" + beginPort + "]绑定失败！");
            }
        });
    }

}
