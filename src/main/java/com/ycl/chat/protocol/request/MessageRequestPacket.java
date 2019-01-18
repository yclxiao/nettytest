package com.ycl.chat.protocol.request;

import com.ycl.chat.protocol.Packet;
import com.ycl.chat.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 10:49 PM
 * Desc: 类描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
