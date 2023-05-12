package com.startersuite.core.dozerconverter;

import java.time.LocalTime;
import org.dozer.DozerConverter;

public class LocalTimeToDateDozerConverter extends
    DozerConverter<LocalTime, LocalTime> {


    public LocalTimeToDateDozerConverter() {
        super(LocalTime.class, LocalTime.class);
    }

    @Override
    public LocalTime convertFrom(LocalTime source, LocalTime destination) {
        LocalTime dateTime = source;
        return dateTime;
    }

    @Override
    public LocalTime convertTo(LocalTime source, LocalTime destination) {
        LocalTime convertToDate = source;
        return convertToDate;
    }


}
