package com.zdlopez.codepathtodo;

import java.io.Serializable;
import java.util.Calendar;
/**
 * Created by ZachLopez on 7/6/16.
 */
public class Todo {

    // private vars for priority and status
    // set priority to ints for possible sorting later
    private static final Integer HIGH = 2;
    private static final Integer MEDIUM = 1;
    private static final Integer LOW = 0;

    private static final String COMPLETE = "Complete";
    private static final String INCOMPLETE = "Incomplete";

    private String id;
    private String name;
    private Calendar dueDate;
    private Integer priority;
    private String status;

    // default constructor
    public Todo() {
        this.id = "";
        this.name = "";
        this.dueDate = Calendar.getInstance();
        this.priority = LOW;
        this.status = INCOMPLETE;
    }

    //main constructor
    public Todo(String id, String name, Calendar dueDate, Integer priority, String status) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }
}
