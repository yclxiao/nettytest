package com.ycl.chat.protocol.request;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 10:09 AM
 * Desc: 类描述
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
