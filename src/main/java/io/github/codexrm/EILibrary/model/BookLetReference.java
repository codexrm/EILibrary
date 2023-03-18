package io.github.codexrm.EILibrary.model;

public class BookLetReference extends Reference {

    private String author;
    private String howpublished;
    private String address;

    public BookLetReference() {
    }

    public BookLetReference(String title, String year, String month, String note, String author, String howpublished, String address) {
        super(title, year, month, note);
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
