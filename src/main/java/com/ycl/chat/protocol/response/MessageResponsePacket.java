package com.ycl.chat.protocol.response;

import com.ycl.chat.protocol.Packet;
import com.ycl.chat.protocol.command.Command;
import lombok.Data;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 10:51 PM
 * Desc: 类描述
 */
@Data
public class MessageResponsePacket extends Packet {
    private String fromUserId;

    private String fromUserName;
    
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
