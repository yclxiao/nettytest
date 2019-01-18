package com.ycl.chat.protocol.request;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 9:32 AM
 * Desc: 类描述
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
