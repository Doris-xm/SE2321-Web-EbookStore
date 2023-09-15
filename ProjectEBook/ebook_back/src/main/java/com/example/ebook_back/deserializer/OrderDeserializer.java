package com.example.ebook_back.deserializer;

import com.example.ebook_back.entity.MyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

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
            String orderJson = new String(data, "UTF-8");
            // 使用JSON库或其他方法将JSON字符串转换为Order对象
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(orderJson, MyOrder.class);
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
