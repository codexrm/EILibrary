package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;
import io.github.codexrm.EILibrary.enums.ThesisType;

public class ThesisReference extends Reference {

    private String author;
    private String school;
    private ThesisType type;
    private String address;

    public ThesisReference() {
    }

    public ThesisReference(String title, String year, Months month, String note, Integer id, String author, String school, ThesisType type, String address) {
        super(title, year, month, note, id);
        this.author = author;
        this.school = school;
        this.type = type;
        this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public ThesisType getType() { return type; }

    public void setType(ThesisType type) { this.type = type; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }
}
