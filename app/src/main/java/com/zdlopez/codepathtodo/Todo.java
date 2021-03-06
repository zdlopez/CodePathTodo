package com.zdlopez.codepathtodo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ZachLopez on 7/6/16.
 */
public class Todo implements Serializable {

    // private vars for priority and status
    // set priority to ints for possible sorting later
    private static final Integer HIGH = 2;
    private static final Integer MEDIUM = 1;
    private static final Integer LOW = 0;
    public static final String[] PRIORITY_TEXT= {"Low", "Medium", "High"};

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


    //getters and setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDueDate() {
        return this.dueDate;
    }

    public String getDueDateFormated() {
        String strdate = "n/a";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        if (this.dueDate != null) {
            strdate = sdf.format(this.dueDate.getTime());
        }
        return strdate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public String getPriorityString() {
        return PRIORITY_TEXT[this.priority];
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
