package ru.samara.shop.web.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ru.samara.shop.LoggerWrapper;
import ru.samara.shop.util.mapper.HibernateAwareObjectMapper;

import java.io.IOException;
import java.util.List;

import static ru.samara.shop.web.json.JacksonObjectMapper.getMapper;

public class JsonUtil {
    private static final LoggerWrapper LOG = LoggerWrapper.get(JsonUtil.class);

//    private static final ObjectMapper mapper = new HibernateAwareObjectMapper();


    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = getMapper().reader(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw LOG.getIllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw LOG.getIllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}
