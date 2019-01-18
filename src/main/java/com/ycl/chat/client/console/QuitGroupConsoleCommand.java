package com.ycl.chat.client.console;

import com.ycl.chat.protocol.request.QuitGroupRequestPacket;
import com.ycl.chat.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 11:05 AM
 * Desc: 类描述
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
