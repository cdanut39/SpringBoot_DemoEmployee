package com.learning.spring.rest.employees.utils.comparators;

import com.learning.spring.rest.employees.model.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EmployeeComparators {

    private static Comparator<Employee> byFirstName = (o1, o2) -> {
        if (o1.getFirstName().compareTo(o2.getFirstName()) > 0) {
            return 1;
        } else if (o1.getFirstName().compareTo(o2.getFirstName()) < 0) {
            return -1;
        } else return 0;
    };

    private static Comparator<Employee> byCommunityName = (o1, o2) -> {
        if (o1.getCommunity().getCommunityName().compareTo(o2.getCommunity().getCommunityName()) > 0) {
            return 1;
        } else if (o1.getCommunity().getCommunityName().compareTo(o2.getCommunity().getCommunityName()) < 0) {
            return -1;
        } else return 0;
    };

    private static Comparator<Employee> byStartDate = (e1, e2) -> {
        if (e1.getStartDate().isAfter(e2.getStartDate())) {
            return 1;
        } else if (e1.getStartDate().isBefore(e2.getStartDate())) {
            return -1;
        } else return 0;

    };

    static Map<String, Comparator<Employee>> map = new HashMap<>();

    public static Map<String, Comparator<Employee>> getMap() {
        map.put("firstName", byFirstName);
        map.put("community", byCommunityName);
        map.put("startDate", byStartDate);
        return map;
    }

    public static Comparator<Employee> reversed(Comparator<Employee> comparator) {
        return comparator.reversed();
    }
}
