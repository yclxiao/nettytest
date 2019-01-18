package com.ycl.chat.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.ycl.chat.serialize.Serializer;
import com.ycl.chat.serialize.SerializerAlgorithm;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 5:25 PM
 * Desc: 类描述
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serializer(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserializer(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
