package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.MonthsLibrary;

public class BaseR {

    protected String title;
    protected String year;
    protected MonthsLibrary month;
    protected String note;
    protected Integer id;
    private final Validations validations = new Validations();

    public BaseR() { }

    public BaseR(String title, String year, MonthsLibrary month, String note, Integer id) {
        this.title = title;
        this.year =  validations.validateYear(year);
        this.month = month;
        this.note = note;
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = validations.validateYear(year); }

    public MonthsLibrary getMonth() { return month; }

    public void setMonth(MonthsLibrary month) { this.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

}
