package com.ycl.nettytest.demo.protocol.response;

import com.ycl.nettytest.demo.protocol.Packet;
import lombok.Data;

import static com.ycl.nettytest.demo.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:24 PM
 * Desc: 类描述
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
