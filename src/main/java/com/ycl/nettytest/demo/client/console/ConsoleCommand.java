package com.ycl.nettytest.demo.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 6:38 PM
 * Desc: 类描述
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
