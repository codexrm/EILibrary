package io.github.codexrm.EILibrary.model;

public class WebPageReference extends Reference {

    private String author;
    private String howpublished;

    public WebPageReference() {
    }

    public WebPageReference(String title, String year, String month, String note, String author, String howpublished) {
        super(title, year, month, note);
        this.author = author;
        this.howpublished = howpublished;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getHowpublished() { return howpublished; }

    public void setHowpublished(String howpublished) { this.howpublished = howpublished; }
}
