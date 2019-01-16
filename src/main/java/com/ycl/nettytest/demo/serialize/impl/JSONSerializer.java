package com.ycl.nettytest.demo.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.ycl.nettytest.demo.serialize.Serializer;
import com.ycl.nettytest.demo.serialize.SerializerAlgorithm;

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
