package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class WebPageR extends BaseR {

    private String author;
    private String url;
    private final Validations validations = new Validations();

    public WebPageR() {
    }

    public WebPageR(String title, String year, Months month, String note, Integer id, String author, String url) {
        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        if(validations.validateUrl(url))
        this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) {
        if(validations.validateUrl(url))
        this.url = url;
    }
}
