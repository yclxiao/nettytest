package com.ycl.nettytest.demo.protocol.response;

import com.ycl.nettytest.demo.protocol.Packet;
import lombok.Data;

import static com.ycl.nettytest.demo.protocol.command.Command.LOGIN_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 6:29 PM
 * Desc: 类描述
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
