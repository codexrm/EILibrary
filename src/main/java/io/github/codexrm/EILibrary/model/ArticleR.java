package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class ArticleR extends BaseR {

    private String author;
    private String journal;
    private String volume;
    private String number;
    private String pages;
    private String issn;
    private final Validations validations = new Validations();

    public ArticleR() {
    }

    public ArticleR(String title, String year, Months month, String note, Integer id, String author, String journal, String volume, String number, String pages, String issn) {
        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateJournalOrPublihser(journal))
        this.journal = journal;

        if(validations.isNumber(volume))
        this.volume = volume;

        if(validations.validateNumber(number))
        this.number = number;

        if(validations.validatePages(pages))
        this.pages = pages;

        if(validations.validateIssnOrIsbn(issn))
        this.issn = issn;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getJournal() { return journal; }

    public void setJournal(String journal) {
        if(validations.validateJournalOrPublihser(journal))
        this.journal = journal;
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

    public String getPages() { return pages; }

    public void setPages(String pages) {
        if(validations.validatePages(pages))
        this.pages = pages;
    }

    public String getIssn() { return issn; }

    public void setIssn(String issn) {
        if(validations.validateIssnOrIsbn(issn))
        this.issn = issn;
    }
}
