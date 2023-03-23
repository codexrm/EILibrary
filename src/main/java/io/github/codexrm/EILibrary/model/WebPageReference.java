package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class WebPageReference extends Reference {

    private String author;
    private String url;

    public WebPageReference() {
    }

    public WebPageReference(String title, String year, Months month, String note, Integer id, String author, String url) {
        super(title, year, month, note, id);
        this.author = author;
        this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }
}
