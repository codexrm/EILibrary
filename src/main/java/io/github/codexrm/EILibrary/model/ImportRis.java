package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.ThesisTypeLibrary;
import io.github.codexrm.jris.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class ImportRis implements Import {

    private final RisManager manager;

    public ImportRis() {
        this.manager = new RisManager();
    }


    @Override
    public ArrayList<BaseR> readFile(String path) throws IOException, TokenMgrException, ParseException {
        ArrayList<BaseR> listBaseR = new ArrayList<>();

        Reader reader = new FileReader(path);
        ArrayList<BaseReference> list = manager.importReferences(reader);

        for (BaseReference entry : list) {
            listBaseR.add(createReference(entry));
        }
        return listBaseR;
    }

    private BaseR createReference(BaseReference entry) {

        BaseR baseR;
        if (entry instanceof JournalArticle) {
            baseR = readArticleReference((JournalArticle) entry);
        } else {
            if (entry instanceof Book) {
                baseR = readBookReference((Book) entry);
            } else {
                if (entry instanceof BookSection) {
                    baseR = readBookSectionReference((BookSection) entry);
                } else {
                    if (entry instanceof Thesis) {
                        baseR = readThesisReference((Thesis) entry);
                    } else {
                        if (entry instanceof ConferenceProceedings) {
                            baseR = readConferenceProceedingsReference((ConferenceProceedings) entry);
                        }else {
                            if (entry instanceof ConferencePaper) {
                                baseR = readConferencePaperReference((ConferencePaper) entry);
                            }else {
                                if (entry instanceof WebPage) {
                                    baseR = readWebPageReference((WebPage) entry);
                                } else {
                                    baseR = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return baseR;
    }

    private void commonField(BaseReference entry, BaseR baseR) {
        baseR.setNote(entry.getNotes());
    }

    private String authorOrEditorField(ArrayList<String> people) {
        if(people.size() == 0)
            return null;

        String authors = people.get(0);
        for (int i = 1; i < people.size(); i++) {
            authors = authors + ";" + people.get(i);
        }
        return authors;
    }

    private BaseR readArticleReference(JournalArticle entry) {

        ArticleR article = new ArticleR();

        commonField(entry, article);
        article.setAuthor(authorOrEditorField(entry.getAuthorList()));
        article.setTitle(entry.getTitle());
        article.setJournal(entry.getJournal());
        article.setYear(entry.getYear());
        article.setVolume(entry.getVolume());
        article.setNumber(entry.getNumber());
        article.setPages(entry.getPages());
        article.setIssn(entry.getIssn());

        return article;
    }

    private BaseR readBookReference(Book entry) {

        BookR book = new BookR();

        commonField(entry, book);
        book.setAuthor(authorOrEditorField(entry.getAuthorList()));
        book.setEditor(authorOrEditorField(entry.getEditorList()));
        book.setTitle(entry.getTitle());
        book.setPublisher(entry.getPublisher());
        book.setYear(entry.getYear());
        book.setVolume(entry.getVolume());
        book.setNumber(entry.getNumber());
        book.setSeries(entry.getSeries());
        book.setAddress(entry.getAddress());
        book.setEdition(entry.getEdition());
        book.setIsbn(entry.getIsbn());

        return book;
    }

    private BaseR readBookSectionReference(BookSection entry) {

        BookSectionR section = new BookSectionR();

        commonField(entry, section);
        section.setChapter(entry.getChapter());
        section.setPages(entry.getPages());
        section.setAuthor(authorOrEditorField(entry.getAuthorList()));
        section.setEditor(authorOrEditorField(entry.getEditorList()));
        section.setTitle(entry.getTitle());
        section.setPublisher(entry.getPublisher());
        section.setYear(entry.getYear());
        section.setVolume(entry.getVolume());
        section.setNumber(entry.getNumber());
        section.setSeries(entry.getSeries());
        section.setAddress(entry.getAddress());
        section.setEdition(entry.getEdition());
        section.setIsbn(entry.getIsbn());

        return section;
    }

    private BaseR readThesisReference(Thesis entry) {

        ThesisR thesis = new ThesisR();

        commonField(entry, thesis);
        thesis.setAuthor(authorOrEditorField(entry.getAuthorList()));
        thesis.setTitle(entry.getTitle());
        thesis.setSchool(entry.getSchool());
        thesis.setYear(entry.getYear());
        if (entry.getType().equals("Masters")) {
            thesis.setType(ThesisTypeLibrary.MASTERS);
        } else {
            if (entry.getType().equals("Phd")) {
                thesis.setType(ThesisTypeLibrary.PHD);
            }
        }
        thesis.setAddress(entry.getAddress());

        return thesis;
    }

    private BaseR readConferenceProceedingsReference(ConferenceProceedings entry) {

        ConferenceProceedingsR proceedings = new ConferenceProceedingsR();
        commonField(entry, proceedings);
        proceedings.setTitle(entry.getTitle());
        proceedings.setYear(entry.getYear());
        proceedings.setEditor(authorOrEditorField(entry.getEditorList()));
        proceedings.setVolume(entry.getVolume());
        proceedings.setNumber(entry.getNumber());
        proceedings.setSeries(entry.getSeries());
        proceedings.setAddress(entry.getAddress());
        proceedings.setPublisher(entry.getPublisher());

        return proceedings;
    }
    private BaseR readConferencePaperReference(ConferencePaper entry) {

        ConferencePaperR paper = new ConferencePaperR();
        commonField(entry, paper);
        paper.setAuthor(authorOrEditorField(entry.getAuthorList()));
        paper.setTitle(entry.getTitle());
        paper.setYear(entry.getYear());
        paper.setEditor(authorOrEditorField(entry.getEditorList()));
        paper.setVolume(entry.getVolume());
        paper.setPages(entry.getPages());
        paper.setAddress(entry.getAddress());
        paper.setPublisher(entry.getPublisher());

        return paper;
    }

    private BaseR readWebPageReference(WebPage entry) {

        WebPageR webPage = new WebPageR();
        commonField(entry, webPage);
        webPage.setAuthor(authorOrEditorField(entry.getAuthorList()));
        webPage.setTitle(entry.getTitle());
        webPage.setYear(entry.getYear());
        webPage.setUrl(entry.getUrl());

        return webPage;
    }
}
