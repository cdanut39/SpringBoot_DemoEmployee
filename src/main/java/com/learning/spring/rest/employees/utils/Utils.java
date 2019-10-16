package com.learning.spring.rest.employees.utils;

import java.util.Calendar;

public class Utils {
    public static Calendar tokenExpiryTime() {
        Calendar now=Calendar.getInstance();
        now.add(Calendar.HOUR,24);
        return now;
    }
}
