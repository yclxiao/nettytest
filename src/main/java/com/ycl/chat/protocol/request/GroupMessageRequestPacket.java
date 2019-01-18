package com.ycl.chat.protocol.request;

import com.ycl.chat.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ycl.chat.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 11:30 AM
 * Desc: 类描述
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
