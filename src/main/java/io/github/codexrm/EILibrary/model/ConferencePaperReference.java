package io.github.codexrm.EILibrary.model;

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

    public ConferencePaperReference() { }

    public ConferencePaperReference(String title, String year, String month, String note, String author, String bookTitle, String editor, String volume, String number, String series, String pages, String address, String organization, String publisher) {
        super(title, year, month, note);
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

    public void setAuthor(String author) { this.author = author; }

    public String getBookTitle() { return bookTitle; }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getEditor() { return editor; }

    public void setEditor(String editor) { this.editor = editor; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getSeries() { return series; }

    public void setSeries(String series) { this.series = series; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { this.organization = organization; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }
}
