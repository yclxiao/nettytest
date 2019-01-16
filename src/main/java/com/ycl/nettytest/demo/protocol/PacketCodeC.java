package com.ycl.nettytest.demo.protocol;

import com.ycl.nettytest.demo.protocol.Packet;
import com.ycl.nettytest.demo.protocol.command.Command;
import com.ycl.nettytest.demo.protocol.request.LoginRequestPacket;
import com.ycl.nettytest.demo.protocol.response.LoginResponsePacket;
import com.ycl.nettytest.demo.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 5:35 PM
 * Desc: 类描述
 */
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x123456;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Serializer> serializerMap;
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private PacketCodeC() {
        serializerMap = new HashMap<>();
        serializerMap.put(Serializer.JSON_SERIALIZER, Serializer.DEFAULT);
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
    }

    public ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();

        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        //跳过魔数字
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //获取序列化算法
        byte serializerAlgorithm = byteBuf.readByte();
        //获取指令
        byte command = byteBuf.readByte();
        //获取数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

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
