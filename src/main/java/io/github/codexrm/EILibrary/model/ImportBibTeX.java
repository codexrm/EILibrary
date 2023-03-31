package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.*;
import org.jbibtex.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ImportBibTeX implements Import {
    @Override
    public ArrayList<Reference> readFile(String path) throws IOException, TokenMgrException, ParseException {
        Reader reader = new FileReader(path);
        BibTeXParser bibtexParser = new BibTeXParser();
        BibTeXDatabase database = bibtexParser.parseFully(reader);
        Map<Key, BibTeXEntry> entryMap = database.getEntries();
        Collection<BibTeXEntry> entries = entryMap.values();
        ArrayList<Reference> listReference = new ArrayList<>();

        Reference reference = new Reference();
        for (BibTeXEntry entry : entries) {

            if (entry.getType().toString().equalsIgnoreCase("article")) {
                reference = createArticleReference(entry);
            } else {
                if (entry.getType().toString().equalsIgnoreCase("book")) {
                    reference = createBookReference(entry);
                } else {
                    if (entry.getType().toString().equalsIgnoreCase("inbook")) {
                        reference = createBookSectionReference(entry);
                    } else {
                        if (entry.getType().toString().equalsIgnoreCase("booklet")) {
                            reference = createBookLetReference(entry);
                        } else {
                            if (entry.getType().toString().equalsIgnoreCase("mastersthesis")
                                    || entry.getType().toString().equalsIgnoreCase("phdthesis")) {
                                reference = createThesisReference(entry);
                            } else {
                                if (entry.getType().toString().equalsIgnoreCase("proceedings")) {
                                    reference = createConferenceProceedingsReference(entry);
                                }else{
                                    if (entry.getType().toString().equalsIgnoreCase("conference") || entry.getType().toString().equalsIgnoreCase("InProceedings")) {
                                        reference = createConferencePaperReference(entry);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (reference != null) {
                listReference.add(reference);
            }
            reference = null;
        }
        return listReference;
    }

    private String createAuthorOrEditorField(String content) {

        String[] people = content.split(" and ", 2);
        StringBuilder person = new StringBuilder(people[0]);

        for (int i = 1; i < people.length; i++) {
            person.append(";").append(people[i]);
        }
        return person.toString();
    }

    private void commonField(BibTeXEntry entry, Reference reference) {

        Value value = entry.getField(BibTeXEntry.KEY_TITLE);
        if (value != null) {
            reference.setTitle(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_YEAR);
        if (value != null ) {
            reference.setYear(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_MONTH);
        if (value != null) {
            reference.setMonth(getMonth(value.toUserString()));
        }

        value = entry.getField(BibTeXEntry.KEY_NOTE);
        if (value != null) {
            reference.setNote(value.toUserString());
        }
    }

    private Months getMonth(String date) {

        if(isNumber(date)) {

            switch (date) {
                case "01":
                    return Months.jan;
                case "02":
                    return Months.feb;
                case "03":
                    return Months.mar;
                case "04":
                    return Months.apr;
                case "05":
                    return Months.may;
                case "06":
                    return Months.jun;
                case "07":
                    return Months.jul;
                case "08":
                    return Months.aug;
                case "09":
                    return Months.sep;
                case "10":
                    return Months.oct;
                case "11":
                    return Months.nov;
                case "12":
                    return Months.dec;
            }
        }else{
                switch (date) {
                    case "jan":
                    case "January":
                        return Months.jan;
                    case "feb":
                    case "February":
                        return Months.feb;
                    case "mar":
                    case "March":
                        return Months.mar;
                    case "apr":
                    case "April":
                        return Months.apr;
                    case "may":
                    case "May":
                        return Months.may;
                    case "jun":
                    case "June":
                        return Months.jun;
                    case "jul":
                    case "July":
                        return Months.jul;
                    case "aug":
                    case "August":
                        return Months.aug;
                    case "sep":
                    case "September":
                        return Months.sep;
                    case "oct":
                    case "October":
                        return Months.oct;
                    case "nov":
                    case "November":
                        return Months.nov;
                    case "dec":
                    case "December":
                        return Months.dec;
                }
            }

        return null;
    }

    private boolean isNumber(final String number) {
        try {
            Long.valueOf(number);
            return true;

        } catch (final NumberFormatException e) {
            return false;
        }
    }
    private void setBookSectionType(final BibTeXEntry entry, BookSectionReference bookSection) {

        Value value = entry.getField(BibTeXEntry.KEY_TYPE);
        switch (value.toString()) {
            case "Master´s thesis":
                bookSection.setType(BookSectionType.MATHESIS);
            case "PhD thesis":
                bookSection.setType(BookSectionType.PHDTHESIS);
            case "Candidate thesis":
                bookSection.setType(BookSectionType.CANDTHESIS);
            case "Technical report":
                bookSection.setType(BookSectionType.TECHREPORT);
            case "Research report":
                bookSection.setType(BookSectionType.RESREPORT);
            case "Software":
                bookSection.setType(BookSectionType.SOFTWARE);
            case "Audio CD":
                bookSection.setType(BookSectionType.AUDIOCD);
            case "Data CD":
                bookSection.setType(BookSectionType.DataCD);
        }
    }

    private Reference createArticleReference(BibTeXEntry entry) {
        ArticleReference article = new ArticleReference();
        commonField(entry, article);
        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null) {
            article.setAuthor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_JOURNAL);
        if (value != null) {
            article.setJournal(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            article.setVolume(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null) {
            article.setNumber(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null) {
            article.setPages(value.toUserString());
        }

        return article;
    }

    private Reference createBookReference(BibTeXEntry entry) {
        BookReference book = new BookReference();
        createBook(entry, book);
        return book;
    }

    private void createBook(BibTeXEntry entry, BookReference book) {
        commonField(entry, book);
        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null) {
            book.setAuthor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_EDITOR);
        if (value != null) {
            book.setEditor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null) {
            book.setPublisher(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            book.setVolume(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null) {
            book.setNumber(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null) {
            book.setSeries(value.toUserString());
        }
         value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            book.setAddress(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_EDITION);
        if (value != null) {
            book.setEdition(value.toUserString());
        }
    }

    private Reference createBookSectionReference(BibTeXEntry entry) {
        BookSectionReference bookSection = new BookSectionReference();
        createBook(entry, bookSection);
        Value value = entry.getField(BibTeXEntry.KEY_CHAPTER);
        if (value != null) {
            bookSection.setChapter(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null) {
            bookSection.setPages(value.toUserString());
        }
        setBookSectionType(entry, bookSection);

        return bookSection;
    }

    private Reference createBookLetReference(BibTeXEntry entry) {
        BookLetReference bookLet = new BookLetReference();
        commonField(entry, bookLet);
        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null) {
            bookLet.setAuthor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_HOWPUBLISHED);
        if (value != null) {
            bookLet.setHowpublished(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            bookLet.setAddress(value.toUserString());
        }
        return bookLet;
    }

    private Reference createThesisReference(BibTeXEntry entry) {
        ThesisReference thesis = new ThesisReference();
        commonField(entry, thesis);
        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null) {
            thesis.setAuthor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_SCHOOL);
        if (value != null) {
            thesis.setSchool(value.toUserString());
        }
        if (entry.getType().getValue().equals("mastersthesis")) {
            thesis.setType(ThesisType.MASTERS);
        } else {
            thesis.setType(ThesisType.PHD);
        }
        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            thesis.setAddress(value.toUserString());
        }
        return thesis;
    }

    private Reference createConferenceProceedingsReference(BibTeXEntry entry) {

        ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference();
        commonField(entry, proceedings);
        Value value = entry.getField(BibTeXEntry.KEY_EDITOR);
        if (value != null) {
            proceedings.setEditor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            proceedings.setVolume(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null) {
            proceedings.setNumber(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null) {
            proceedings.setSeries(value.toUserString());
        }
         value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            proceedings.setAddress(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null) {
            proceedings.setPublisher(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_ORGANIZATION);
        if (value != null) {
            proceedings.setOrganization(value.toUserString());
        }
        return proceedings;
    }

    private Reference createConferencePaperReference(BibTeXEntry entry) {

        ConferencePaperReference paper = new ConferencePaperReference();
        commonField(entry, paper);
        Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
        if (value != null) {
            paper.setAuthor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_BOOKTITLE);
        if (value != null) {
            paper.setBookTitle(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_EDITOR);
        if (value != null) {
            paper.setEditor(createAuthorOrEditorField(value.toUserString()));
        }
        value = entry.getField(BibTeXEntry.KEY_VOLUME);
        if (value != null) {
            paper.setVolume(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_NUMBER);
        if (value != null) {
            paper.setNumber(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_SERIES);
        if (value != null) {
            paper.setSeries(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PAGES);
        if (value != null) {
            paper.setPages(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            paper.setAddress(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_ORGANIZATION);
        if (value != null) {
            paper.setOrganization(value.toUserString());
        }
        value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
        if (value != null) {
            paper.setPublisher(value.toUserString());
        }
        return paper;
    }

}
