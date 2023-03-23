package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class Reference {

    protected String title;
    protected String year;
    protected Months month;
    protected String note;
    protected Integer id;

    public Reference() { }

    public Reference(String title, String year, Months month, String note, Integer id) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public Months getMonth() { return month; }

    public void setMonth(Months month) { this.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
}
