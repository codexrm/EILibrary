package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.MonthsLibrary;
import io.github.codexrm.EILibrary.enums.ThesisTypeLibrary;

public class ThesisR extends BaseR {

    private String author;
    private String school;
    private ThesisTypeLibrary type;
    private String address;
    private final Validations validations = new Validations();

    public ThesisR() {
    }

    public ThesisR(String title, String year, MonthsLibrary month, String note, Integer id, String author, String school, ThesisTypeLibrary type, String address) {
        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateSchool(school))
        this.school = school;

        this.type = type;

        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getSchool() { return school; }

    public void setSchool(String school) {
        if(validations.validateSchool(school))
        this.school = school;
    }

    public ThesisTypeLibrary getType() { return type; }

    public void setType(ThesisTypeLibrary type) { this.type = type; }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }
}
