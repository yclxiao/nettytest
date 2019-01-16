package com.ycl.nettytest.demo.protocol.request;

import com.ycl.nettytest.demo.protocol.Packet;
import com.ycl.nettytest.demo.protocol.command.Command;
import lombok.Data;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 10:49 PM
 * Desc: 类描述
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
