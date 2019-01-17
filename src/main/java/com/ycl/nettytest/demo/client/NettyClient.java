package com.ycl.nettytest.demo.client;

import com.ycl.nettytest.demo.client.handler.LoginResponseHandler;
import com.ycl.nettytest.demo.client.handler.MessageResponseHandler;
import com.ycl.nettytest.demo.codec.PacketDecoder;
import com.ycl.nettytest.demo.codec.PacketEncoder;
import com.ycl.nettytest.demo.codec.Spliter;
import com.ycl.nettytest.demo.protocol.request.MessageRequestPacket;
import com.ycl.nettytest.demo.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 2:41 PM
 * Desc: 类描述
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final int BEGIN_PORT = 8000;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("initChannel");
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });

        connectServer(bootstrap, HOST, BEGIN_PORT, MAX_RETRY);
    }

    private static void connectServer(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("链接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);

            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connectServer(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    System.out.println("输入消息发送至服务端: " + line);

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage(line);

                    for (int i = 0; i < 1000; i++) {

                        channel.writeAndFlush(messageRequestPacket);
                    }
                }
            }
        }).start();
    }
}
