package com.ycl.chat.protocol.request;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.LOGOUT_REQUEST;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:22 PM
 * Desc: 类描述
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
