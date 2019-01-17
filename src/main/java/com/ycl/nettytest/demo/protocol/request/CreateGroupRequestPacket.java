package com.ycl.nettytest.demo.protocol.request;

import com.ycl.nettytest.demo.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.ycl.nettytest.demo.protocol.command.Command.CREATE_GROUP_REQUEST;

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
