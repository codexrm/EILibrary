package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class ConferencePaperReference extends Reference {

    private String author;
    private String bookTitle;
    private String editor;
    private String volume;
    private String number;
    private String series;
    private String pages;
    private String address;
    private String organization;
    private String publisher;
    private final Validations validations = new Validations();

    public ConferencePaperReference() { }

    public ConferencePaperReference(String title, String year, Months month, String note, Integer id, String author, String bookTitle, String editor, String volume, String number, String series, String pages, String address, String organization, String publisher) {
        super(title, year, month, note, id);
        this.author = author;
        this.bookTitle = bookTitle;
        this.editor = editor;
        this.volume = volume;
        this.number = number;
        this.series = series;
        this.pages = pages;
        this.address = address;
        this.organization = organization;
        this.publisher = publisher;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getBookTitle() { return bookTitle; }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

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

    public String getPages() { return pages; }

    public void setPages(String pages) {
        if(validations.validatePages(pages))
        this.pages = pages;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) {
        if(validations.validatePublisherOrOrganizationOrSeries(organization))
        this.organization = organization;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) {
        if(validations.validatePublisherOrOrganizationOrSeries(publisher))
        this.publisher = publisher;
    }
}
