package com.ycl.nettytest.demo.util;

import com.ycl.nettytest.demo.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 10:55 PM
 * Desc: 类描述
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
