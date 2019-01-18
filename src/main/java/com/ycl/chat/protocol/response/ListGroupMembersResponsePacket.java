package com.ycl.chat.protocol.response;

import com.ycl.chat.protocol.Packet;
import com.ycl.chat.session.Session;
import lombok.Data;

import java.util.List;

import static com.ycl.chat.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 9:36 AM
 * Desc: 类描述
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
