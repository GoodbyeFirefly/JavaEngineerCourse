package com.xxy.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DateConverterConfig implements Converter<String, Date> {
    private static final List<String> formarts = new ArrayList<>(4);
    static {
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String s) {
        String value = s.trim();
        if ("".equals(value)) {
            return null;
        }
        if (s.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(s, formarts.get(0));
        } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(s, formarts.get(1));
        } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(s, formarts.get(2));
        } else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(s, formarts.get(3));
        } else {
            throw new IllegalArgumentException("Invalid boolean value '" + s + "'");
        }
    }

    public Date parseDate(String dataStr, String format) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
