package com.ycl.chat.protocol.request;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 10:40 AM
 * Desc: 类描述
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
