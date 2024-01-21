package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.MonthsLibrary;

public class ConferenceProceedingsR extends BaseR {

    private String editor;
    private String volume;
    private String number;
    private String series;
    private String address;
    private String publisher;
    private String organization;
    private String isbn;
    private final Validations validations = new Validations();

    public ConferenceProceedingsR() {
    }

    public ConferenceProceedingsR(String title, String year, MonthsLibrary month, String note, Integer id, String editor, String volume, String number, String series, String address, String publisher, String organization, String isbn) {
        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;

        if(validations.isNumber(volume))
        this.volume = volume;

        if(validations.validateNumber(number))
        this.number = number;

        if(validations.validateSeries(series))
        this.series = series;

        if(validations.validateAddress(address))
        this.address = address;

        this.publisher = publisher;

        this.organization = organization;

        if(validations.validateIsbn(isbn))
        this.isbn = isbn;
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;
    }

    public String getVolume() { return volume; }

    public void setVolume(String volume) {
        if(validations.isNumber(volume))
        this.volume = volume;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) {
        if(validations.validateNumber(number))
        this.number = number;
    }

    public String getSeries() { return series; }

    public void setSeries(String series) {
        if(validations.validateSeries(series))
        this.series = series;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { this.organization = organization; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if(validations.validateIsbn(isbn))
        this.isbn = isbn;
    }
}
