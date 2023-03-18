package io.github.codexrm.EILibrary.model;

public class Reference {

    protected String title;
    protected String year;
    protected String month;
    protected String note;

    public Reference() { }

    public Reference(String title, String year, String month, String note) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.note = note;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}
