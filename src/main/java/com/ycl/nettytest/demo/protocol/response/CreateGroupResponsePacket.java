package com.ycl.nettytest.demo.protocol.response;

import com.ycl.nettytest.demo.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.ycl.nettytest.demo.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/17
 * Time: 10:35 PM
 * Desc: 类描述
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
