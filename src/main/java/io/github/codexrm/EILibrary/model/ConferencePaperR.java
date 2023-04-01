package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.MonthsLibrary;

public class ConferencePaperR extends BaseR {

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

    public ConferencePaperR() { }

    public ConferencePaperR(String title, String year, MonthsLibrary month, String note, Integer id, String author, String bookTitle, String editor, String volume, String number, String series, String pages, String address, String organization,
                            String publisher) {

        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        this.bookTitle = bookTitle;

        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;

        if(validations.isNumber(volume))
        this.volume = volume;

        if(validations.validateNumber(number))
        this.number = number;

        if(validations.validateOrganizationOrSeries(series))
        this.series = series;

        if(validations.validatePages(pages))
        this.pages = pages;

        if(validations.validateAddress(address))
        this.address = address;

        if(validations.validateOrganizationOrSeries(organization))
        this.organization = organization;

        if(validations.validateJournalOrPublihser(publisher))
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
        if(validations.validateOrganizationOrSeries(series))
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
        if(validations.validateOrganizationOrSeries(organization))
        this.organization = organization;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) {
        if(validations.validateJournalOrPublihser(publisher))
        this.publisher = publisher;
    }
}
