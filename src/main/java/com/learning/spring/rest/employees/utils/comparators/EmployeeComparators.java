package com.learning.spring.rest.employees.utils.comparators;

import com.learning.spring.rest.employees.model.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EmployeeComparators {


    static Comparator<Employee> bySalary = (o1, o2) -> {
        if (o1.getSalary() - o2.getSalary() > 0) {
            return 1;
        } else if (o1.getSalary() - o2.getSalary() < 0) {
            return -1;
        } else return 0;
    };

    static Comparator<Employee> byFirstName = (o1, o2) -> {
        if (o1.getFirstName().compareTo(o2.getFirstName()) > 0) {
            return 1;
        } else if (o1.getFirstName().compareTo(o2.getFirstName()) < 0) {
            return -1;
        } else return 0;
    };

    static Comparator<Employee> byCommunityName = (o1, o2) -> {
        if (o1.getCommunityName().compareTo(o2.getCommunityName()) > 0) {
            return 1;
        } else if (o1.getCommunityName().compareTo(o2.getCommunityName()) < 0) {
            return -1;
        } else return 0;
    };

    static Map<String, Comparator<Employee>> map = new HashMap<>();

    public static Map<String, Comparator<Employee>> getMap() {
        map.put("salary", bySalary);
        map.put("firstName", byFirstName);
        map.put("community", byCommunityName);
        return map;
    }

    public static Comparator<Employee> reversed(Comparator<Employee> comparator) {
        return comparator.reversed();
    }
}
