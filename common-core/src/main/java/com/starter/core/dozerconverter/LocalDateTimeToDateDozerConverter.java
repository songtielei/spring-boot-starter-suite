package com.starter.core.dozerconverter;

import java.time.LocalDateTime;
import org.dozer.DozerConverter;

public class LocalDateTimeToDateDozerConverter extends DozerConverter<LocalDateTime, LocalDateTime> {


    public LocalDateTimeToDateDozerConverter() {
        super(LocalDateTime.class, LocalDateTime.class);
    }

    @Override
    public LocalDateTime convertFrom(LocalDateTime source, LocalDateTime destination) {
        LocalDateTime dateTime = source;
        return dateTime;
    }

    @Override
    public LocalDateTime convertTo(LocalDateTime source, LocalDateTime destination) {
        LocalDateTime convertToDate = source;
        return convertToDate;
    }



}
