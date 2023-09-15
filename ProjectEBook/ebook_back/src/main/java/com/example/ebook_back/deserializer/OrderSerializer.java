package com.example.ebook_back.deserializer;

import com.alibaba.fastjson.JSON;
import com.example.ebook_back.entity.MyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class OrderSerializer implements Serializer<MyOrder> {
    @Override
    public byte[] serialize(String topic, MyOrder data) {
        // 将字节数组转换为Order对象
        if (data == null) {
            return null;
        }
        try {
            String json = JSON.toJSONString(data);
            return json.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理反序列化异常
        }
        return null;
    }
    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {}

}
