package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

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

    public ConferenceProceedingsR(String title, String year, Months month, String note, Integer id, String editor, String volume, String number, String series, String address, String publisher, String organization, String isbn) {
        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;

        if(validations.isNumber(volume))
        this.volume = volume;

        if(validations.validateNumber(number))
        this.number = number;

        if(validations.validateOrganizationOrSeries(series))
        this.series = series;

        if(validations.validateAddress(address))
        this.address = address;

        if(validations.validateJournalOrPublihser(publisher))
        this.publisher = publisher;

        if(validations.validateOrganizationOrSeries(organization))
        this.organization = organization;

        if(validations.validateIssnOrIsbn(isbn))
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
        if(validations.validateOrganizationOrSeries(series))
        this.series = series;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) {
        if(validations.validateJournalOrPublihser(publisher))
        this.publisher = publisher;
    }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) {
        if(validations.validateOrganizationOrSeries(organization))
        this.organization = organization;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if(validations.validateIssnOrIsbn(isbn))
        this.isbn = isbn;
    }
}
