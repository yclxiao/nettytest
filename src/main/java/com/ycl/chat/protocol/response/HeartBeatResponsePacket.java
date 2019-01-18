package com.ycl.chat.protocol.response;

import com.ycl.chat.protocol.Packet;
import lombok.Data;

import static com.ycl.chat.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/18
 * Time: 2:35 PM
 * Desc: 类描述
 */
@Data
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
