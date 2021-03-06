package com.ycl.chat.protocol.response;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.LOGIN_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 6:29 PM
 * Desc: 类描述
 */
@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
