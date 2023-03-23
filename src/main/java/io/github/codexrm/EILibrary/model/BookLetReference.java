package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class BookLetReference extends Reference {

    private String author;
    private String howpublished;
    private String address;

    public BookLetReference() {
    }

    public BookLetReference(String title, String year, Months month, String note, Integer id, String author, String howpublished, String address) {
        super(title, year, month, note, id);
        this.author = author;
        this.howpublished = howpublished;
        this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getHowpublished() { return howpublished; }

    public void setHowpublished(String howpublished) { this.howpublished = howpublished; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }
}
