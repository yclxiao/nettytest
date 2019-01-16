package com.ycl.nettytest.demo.protocol.command;

import lombok.Data;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 5:19 PM
 * Desc: 类描述
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
