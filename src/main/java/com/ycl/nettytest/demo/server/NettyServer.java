package com.ycl.nettytest.demo.server;

import com.ycl.nettytest.demo.codec.PacketDecoder;
import com.ycl.nettytest.demo.codec.PacketEncoder;
import com.ycl.nettytest.demo.server.handler.LoginRequestHandler;
import com.ycl.nettytest.demo.server.handler.MessageRequestHandler;
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
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
                        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
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
