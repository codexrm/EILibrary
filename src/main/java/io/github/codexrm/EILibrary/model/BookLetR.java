package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.MonthsLibrary;

public class BookLetR extends BaseR {

    private String author;
    private String howpublished;
    private String address;
    private final Validations validations = new Validations();

    public BookLetR() {
    }

    public BookLetR(String title, String year, MonthsLibrary month, String note, Integer id, String author, String howpublished, String address) {
        super(title, year, month, note, id);

        if(validations.validateAuthorOrEditor(author))
        this.author = author;

        this.howpublished = howpublished;

        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getHowpublished() { return howpublished; }

    public void setHowpublished(String howpublished) { this.howpublished = howpublished; }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }
}
