package com.example.webshixun.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;


@JsonComponent
public class JacksonConfig {

    /**
     * Java 的 long 类型在 JavaScript 中会溢出，这里统一把 long 转为 string 进行返回
     */
    public static class LongToStringSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(String.valueOf(value));
        }
    }

}
