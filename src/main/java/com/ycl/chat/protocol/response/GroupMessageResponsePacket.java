package com.ycl.chat.protocol.response;

import com.ycl.chat.protocol.Packet;
import com.ycl.chat.session.Session;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 11:31 AM
 * Desc: 类描述
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
