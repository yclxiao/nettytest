package com.ycl.chat.protocol;

import com.ycl.chat.protocol.command.Command;
import com.ycl.chat.protocol.request.*;
import com.ycl.chat.protocol.response.*;
import com.ycl.chat.serialize.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 5:35 PM
 * Desc: 类描述
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x123456;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Serializer> serializerMap;
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private PacketCodeC() {
        serializerMap = new HashMap<>();
        serializerMap.put(Serializer.JSON_SERIALIZER, Serializer.DEFAULT);
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
    }

    public void encode(ByteBuf out,Packet packet) {

        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        out.writeInt(MAGIC_NUMBER);
        out.writeByte(packet.getVersion());
        out.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        out.writeByte(packet.getCommand());
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }

    public Packet decode(ByteBuf in) {
        //跳过魔数字
        in.skipBytes(4);
        //跳过版本号
        in.skipBytes(1);
        //获取序列化算法
        byte serializerAlgorithm = in.readByte();
        //获取指令
        byte command = in.readByte();
        //获取数据包长度
        int length = in.readInt();

        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Serializer serializer = getSerializer(serializerAlgorithm);
        Class<? extends Packet> requestType = getRequestType(command);

        if (serializer != null && requestType != null) {
            return serializer.deserializer(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

    /*public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }*/
}
