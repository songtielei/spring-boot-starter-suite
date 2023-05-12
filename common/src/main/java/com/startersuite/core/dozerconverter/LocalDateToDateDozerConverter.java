package com.startersuite.core.dozerconverter;

import java.time.LocalDate;
import org.dozer.DozerConverter;

public class LocalDateToDateDozerConverter extends DozerConverter<LocalDate, LocalDate> {


    public LocalDateToDateDozerConverter() {
        super(LocalDate.class, LocalDate.class);
    }

    @Override
    public LocalDate convertFrom(LocalDate source, LocalDate destination) {
        LocalDate dateTime = source;
        return dateTime;
    }

    @Override
    public LocalDate convertTo(LocalDate source, LocalDate destination) {
        LocalDate convertToDate = source;
        return convertToDate;
    }



}
