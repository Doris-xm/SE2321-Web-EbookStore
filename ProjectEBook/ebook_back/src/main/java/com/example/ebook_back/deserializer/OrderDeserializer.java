package com.example.ebook_back.deserializer;

import com.alibaba.fastjson.JSON;
import com.example.ebook_back.entity.MyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class OrderDeserializer implements Deserializer<MyOrder> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // 配置反序列化器
    }

    @Override
    public MyOrder deserialize(String topic, byte[] data) {
        // 将字节数组转换为Order对象
        if (data == null) {
            return null;
        }
        try {
            String json = new String(data, StandardCharsets.UTF_8);
            return JSON.parseObject(json, MyOrder.class);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理反序列化异常
        }
        return null;
    }

    @Override
    public void close() {
        // 关闭资源
    }
}
