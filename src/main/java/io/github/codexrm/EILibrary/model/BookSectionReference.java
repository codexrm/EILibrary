package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.BookSectionType;
import io.github.codexrm.EILibrary.enums.Months;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;
    private BookSectionType type;
    private final Validations validations = new Validations();

    public BookSectionReference() {
    }

    public BookSectionReference(String title, String year, Months month, String note, Integer id, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn, String chapter, String pages, BookSectionType type) {
        super(title, year, month, note, id, author, editor, publisher, volume, number, series, address, edition, isbn);
        this.chapter = chapter;
        this.pages = pages;
        this.type = type;
    }

    public String getChapter() { return chapter; }

    public void setChapter(String chapter) {
        if(validations.isNumber(chapter))
        this.chapter = chapter;
    }

    public String getPages() { return pages; }

    public void setPages(String pages) {
        if(validations.validatePages(pages))
        this.pages = pages;
    }

    public BookSectionType getType() { return type; }

    public void setType(BookSectionType type) {
        this.type = type;
    }
}
