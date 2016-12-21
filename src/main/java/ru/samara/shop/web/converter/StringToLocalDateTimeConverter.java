package ru.samara.shop.web.converter;

import org.springframework.core.convert.converter.Converter;
import ru.samara.shop.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {
        return TimeUtil.toDateTime(source, DateTimeFormatter.ISO_DATE_TIME);
    }
}
