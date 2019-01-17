package com.ycl.nettytest.demo.client.console;

import com.ycl.nettytest.demo.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 7:03 PM
 * Desc: 类描述
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.println("输入用户名登录：");
        loginRequestPacket.setUsername(scanner.nextLine());
        loginRequestPacket.setPassword("pwd");

        channel.writeAndFlush(loginRequestPacket);

        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
