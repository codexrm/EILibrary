package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Months;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;
    private String type;

    public BookSectionReference() {
    }

    public BookSectionReference(String title, String year, Months month, String note, Integer id, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn, String chapter, String pages, String type) {
        super(title, year, month, note, id, author, editor, publisher, volume, number, series, address, edition, isbn);
        this.chapter = chapter;
        this.pages = pages;
        this.type = type;
    }

    public String getChapter() { return chapter; }

    public void setChapter(String chapter) { this.chapter = chapter; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
