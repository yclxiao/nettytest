package com.ycl.chat.protocol.response;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 10:41 AM
 * Desc: 类描述
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
