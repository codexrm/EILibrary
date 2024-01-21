package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.BookSectionTypeLibrary;
import io.github.codexrm.EILibrary.enums.MonthsLibrary;

public class BookSectionR extends BookR {

    private String chapter;
    private String pages;
    private BookSectionTypeLibrary type;
    private final Validations validations = new Validations();

    public BookSectionR() {
    }

    public BookSectionR(String title, String year, MonthsLibrary month, String note, Integer id, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn, String chapter,
                        String pages, BookSectionTypeLibrary type) {

        super(title, year, month, note, id, author, editor, publisher, volume, number, series, address, edition, isbn);

        if(validations.isNumber(chapter))
        this.chapter = chapter;

        if(validations.validatePages(pages))
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

    public BookSectionTypeLibrary getType() { return type; }

    public void setType(BookSectionTypeLibrary type) {
        this.type = type;
    }
}
