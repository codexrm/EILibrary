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
    public ArrayList<BaseR> readFile(String path) throws IOException, TokenMgrException, ParseException {
        Reader reader = new FileReader(path);
        BibTeXParser bibtexParser = new BibTeXParser();
        BibTeXDatabase database = bibtexParser.parseFully(reader);
        Map<Key, BibTeXEntry> entryMap = database.getEntries();
        Collection<BibTeXEntry> entries = entryMap.values();
        ArrayList<BaseR> listBaseR = new ArrayList<>();

        BaseR baseR = new BaseR();
        for (BibTeXEntry entry : entries) {

            if (entry.getType().toString().equalsIgnoreCase("article")) {
                baseR = createArticleReference(entry);
            } else {
                if (entry.getType().toString().equalsIgnoreCase("book")) {
                    baseR = createBookReference(entry);
                } else {
                    if (entry.getType().toString().equalsIgnoreCase("inbook")) {
                        baseR = createBookSectionReference(entry);
                    } else {
                        if (entry.getType().toString().equalsIgnoreCase("booklet")) {
                            baseR = createBookLetReference(entry);
                        } else {
                            if (entry.getType().toString().equalsIgnoreCase("mastersthesis")
                                    || entry.getType().toString().equalsIgnoreCase("phdthesis")) {
                                baseR = createThesisReference(entry);
                            } else {
                                if (entry.getType().toString().equalsIgnoreCase("proceedings")) {
                                    baseR = createConferenceProceedingsReference(entry);
                                }else{
                                    if (entry.getType().toString().equalsIgnoreCase("conference") || entry.getType().toString().equalsIgnoreCase("InProceedings")) {
                                        baseR = createConferencePaperReference(entry);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (baseR != null) {
                listBaseR.add(baseR);
            }
            baseR = null;
        }
        return listBaseR;
    }

    private String createAuthorOrEditorField(String content) {

        String[] people = content.split(" and ", 2);
        StringBuilder person = new StringBuilder(people[0]);

        for (int i = 1; i < people.length; i++) {
            person.append(";").append(people[i]);
        }
        return person.toString();
    }

    private void commonField(BibTeXEntry entry, BaseR baseR) {

        Value value = entry.getField(BibTeXEntry.KEY_TITLE);
        if (value != null) {
            baseR.setTitle(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_YEAR);
        if (value != null ) {
            baseR.setYear(value.toUserString());
        }

        value = entry.getField(BibTeXEntry.KEY_MONTH);
        if (value != null) {
            baseR.setMonth(getMonth(value.toUserString()));
        }

        value = entry.getField(BibTeXEntry.KEY_NOTE);
        if (value != null) {
            baseR.setNote(value.toUserString());
        }
    }

    private MonthsLibrary getMonth(String date) {

        if(isNumber(date)) {

            switch (date) {
                case "01":
                    return MonthsLibrary.jan;
                case "02":
                    return MonthsLibrary.feb;
                case "03":
                    return MonthsLibrary.mar;
                case "04":
                    return MonthsLibrary.apr;
                case "05":
                    return MonthsLibrary.may;
                case "06":
                    return MonthsLibrary.jun;
                case "07":
                    return MonthsLibrary.jul;
                case "08":
                    return MonthsLibrary.aug;
                case "09":
                    return MonthsLibrary.sep;
                case "10":
                    return MonthsLibrary.oct;
                case "11":
                    return MonthsLibrary.nov;
                case "12":
                    return MonthsLibrary.dec;
            }
        }else{
                switch (date) {
                    case "jan":
                    case "January":
                        return MonthsLibrary.jan;
                    case "feb":
                    case "February":
                        return MonthsLibrary.feb;
                    case "mar":
                    case "March":
                        return MonthsLibrary.mar;
                    case "apr":
                    case "April":
                        return MonthsLibrary.apr;
                    case "may":
                    case "May":
                        return MonthsLibrary.may;
                    case "jun":
                    case "June":
                        return MonthsLibrary.jun;
                    case "jul":
                    case "July":
                        return MonthsLibrary.jul;
                    case "aug":
                    case "August":
                        return MonthsLibrary.aug;
                    case "sep":
                    case "September":
                        return MonthsLibrary.sep;
                    case "oct":
                    case "October":
                        return MonthsLibrary.oct;
                    case "nov":
                    case "November":
                        return MonthsLibrary.nov;
                    case "dec":
                    case "December":
                        return MonthsLibrary.dec;
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
    private void setBookSectionType(final BibTeXEntry entry, BookSectionR bookSection) {

        Value value = entry.getField(BibTeXEntry.KEY_TYPE);
        switch (value.toString()) {
            case "Master´s thesis":
                bookSection.setType(BookSectionTypeLibrary.MATHESIS);
            case "PhD thesis":
                bookSection.setType(BookSectionTypeLibrary.PHDTHESIS);
            case "Candidate thesis":
                bookSection.setType(BookSectionTypeLibrary.CANDTHESIS);
            case "Technical report":
                bookSection.setType(BookSectionTypeLibrary.TECHREPORT);
            case "Research report":
                bookSection.setType(BookSectionTypeLibrary.RESREPORT);
            case "Software":
                bookSection.setType(BookSectionTypeLibrary.SOFTWARE);
            case "Audio CD":
                bookSection.setType(BookSectionTypeLibrary.AUDIOCD);
            case "Data CD":
                bookSection.setType(BookSectionTypeLibrary.DataCD);
        }
    }

    private BaseR createArticleReference(BibTeXEntry entry) {
        ArticleR article = new ArticleR();
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

    private BaseR createBookReference(BibTeXEntry entry) {
        BookR book = new BookR();
        createBook(entry, book);
        return book;
    }

    private void createBook(BibTeXEntry entry, BookR book) {
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

    private BaseR createBookSectionReference(BibTeXEntry entry) {
        BookSectionR bookSection = new BookSectionR();
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

    private BaseR createBookLetReference(BibTeXEntry entry) {
        BookLetR bookLet = new BookLetR();
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

    private BaseR createThesisReference(BibTeXEntry entry) {
        ThesisR thesis = new ThesisR();
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
            thesis.setType(ThesisTypeLibrary.MASTERS);
        } else {
            thesis.setType(ThesisTypeLibrary.PHD);
        }
        value = entry.getField(BibTeXEntry.KEY_ADDRESS);
        if (value != null) {
            thesis.setAddress(value.toUserString());
        }
        return thesis;
    }

    private BaseR createConferenceProceedingsReference(BibTeXEntry entry) {

        ConferenceProceedingsR proceedings = new ConferenceProceedingsR();
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

    private BaseR createConferencePaperReference(BibTeXEntry entry) {

        ConferencePaperR paper = new ConferencePaperR();
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
