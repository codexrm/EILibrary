package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.MonthsLibrary;

public class BookR extends BaseR {

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

    public BookR() { }

    public BookR(String title, String year, MonthsLibrary month, String note, Integer id, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn) {
        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;

        this.publisher = publisher;

        if(validations.isNumber(volume))
        this.volume = volume;

        if(validations.validateNumber(number))
        this.number = number;

        if(validations.validateSeries(series))
        this.series = series;

        if(validations.validateAddress(address))
        this.address = address;

        if(validations.validateEdition(edition))
        this.edition = edition;

        if(validations.validateIsbn(isbn))
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

    public void setPublisher(String publisher) { this.publisher = publisher; }

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

    public String getEdition() { return edition; }

    public void setEdition(String edition) {
        if(validations.validateEdition(edition))
        this.edition = edition;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if(validations.validateIsbn(isbn))
        this.isbn = isbn;
    }
}
