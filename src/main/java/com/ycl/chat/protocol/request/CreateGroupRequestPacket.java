package com.ycl.chat.protocol.request;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.ycl.chat.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:34 PM
 * Desc: 类描述
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
