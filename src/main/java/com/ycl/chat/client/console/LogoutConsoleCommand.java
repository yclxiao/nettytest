package com.ycl.chat.client.console;

import com.ycl.chat.protocol.response.LogoutResponsePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:22 PM
 * Desc: 类描述
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutResponsePacket logoutRequestPacket = new LogoutResponsePacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
