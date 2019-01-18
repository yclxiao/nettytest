package com.ycl.chat.serialize;

import com.ycl.chat.serialize.impl.JSONSerializer;

/**
 * User: OF1089 杨成龙
 * Date: 2019/1/16
 * Time: 5:14 PM
 * Desc: 类描述
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    byte[] serializer(Object object);

    /**
     * 二进制转成java对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserializer(Class<T> clazz, byte[] bytes);

}
