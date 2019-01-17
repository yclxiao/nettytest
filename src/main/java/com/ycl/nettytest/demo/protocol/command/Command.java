package com.ycl.nettytest.demo.protocol.command;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 5:20 PM
 * Desc: 类描述
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;

    Byte LOGOUT_REQUEST = 5;
    Byte LOGOUT_RESPONSE = 6;

    Byte CREATE_GROUP_REQUEST = 7;
    Byte CREATE_GROUP_RESPONSE = 8;
}
