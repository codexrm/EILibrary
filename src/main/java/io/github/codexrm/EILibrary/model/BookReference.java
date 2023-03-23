package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class BookReference extends Reference {

    protected String author;
    protected String editor;
    protected String publisher;
    protected String volume;
    protected String number;
    protected String series;
    protected String address;
    protected String edition;
    protected String isbn;
    private final Validations validations = new Validations();

    public BookReference() { }

    public BookReference(String title, String year, Months month, String note, Integer id, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn) {
        super(title, year, month, note, id);
        this.author = author;
        this.editor = editor;
        this.publisher = publisher;
        this.volume = volume;
        this.number = number;
        this.series = series;
        this.address = address;
        this.edition = edition;
        this.isbn = isbn;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) {
        if(validations.validatePublisherOrOrganizationOrSeries(publisher))
        this.publisher = publisher;
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

    public String getEdition() { return edition; }

    public void setEdition(String edition) {
        if(validations.validateEdition(edition))
        this.edition = edition;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if(validations.validateIssnOrIsbn(isbn))
        this.isbn = isbn;
    }
}
