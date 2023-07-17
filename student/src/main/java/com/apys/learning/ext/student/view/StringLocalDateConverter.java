package com.apys.learning.ext.student.view;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringLocalDateConverter extends StdConverter<String, LocalDate> {
    @Override
    public LocalDate convert(String str) {
        if(str == null || str.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(LocalDateStringConverter.DATE_FORMAT));
    }
}
