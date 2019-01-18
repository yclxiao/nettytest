package com.ycl.chat.attribute;

import com.ycl.chat.session.Session;
import io.netty.util.AttributeKey;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 10:46 PM
 * Desc: 类描述
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
