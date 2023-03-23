package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class ConferenceProceedingsReference extends Reference {

    private String editor;
    private String volume;
    private String number;
    private String series;
    private String address;
    private String publisher;
    private String organization;
    private String isbn;
    private final Validations validations = new Validations();

    public ConferenceProceedingsReference() {
    }

    public ConferenceProceedingsReference(String title, String year, Months month, String note, Integer id, String editor, String volume, String number, String series, String address, String publisher, String organization, String isbn) {
        super(title, year, month, note, id);
        this.editor = editor;
        this.volume = volume;
        this.number = number;
        this.series = series;
        this.address = address;
        this.publisher = publisher;
        this.organization = organization;
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
        if(validations.validatePublisherOrOrganizationOrSeries(series))
        this.series = series;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) {
        if(validations.validatePublisherOrOrganizationOrSeries(publisher))
        this.publisher = publisher;
    }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) {
        if(validations.validatePublisherOrOrganizationOrSeries(organization))
        this.organization = organization;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if(validations.validateIssnOrIsbn(isbn))
        this.isbn = isbn;
    }
}
