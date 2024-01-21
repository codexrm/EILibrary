package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.ThesisTypeLibrary;
import io.github.codexrm.jris.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ExportRis implements Export {

    @Override
    public void writeValue(ArrayList<BaseR> baseRList, String path) throws IOException {

        RisManager manager = new RisManager();
        for (BaseR baseR : baseRList) {
            BaseReference entry = identifyType(baseR);
            if (entry != null) {
                manager.addReference(entry);
            }
        }
        manager.exportListReference(path);
    }

    private BaseReference identifyType(BaseR baseR) {

        BaseReference entry;

        if (baseR instanceof ArticleR) {
            entry = createJournal((ArticleR) baseR);

        } else {
            if (baseR instanceof BookSectionR) {
                entry = createBookSection((BookSectionR) baseR);

            } else {
                if (baseR instanceof BookR) {
                    entry = createBook((BookR) baseR);

                } else {
                    if (baseR instanceof ThesisR) {
                        entry = createThesis((ThesisR) baseR);

                    } else {
                        if (baseR instanceof ConferenceProceedingsR) {
                            entry = createConferenceProceedings((ConferenceProceedingsR) baseR);

                        } else {
                            if (baseR instanceof ConferencePaperR) {
                                entry = createConferencePaper((ConferencePaperR) baseR);

                            } else {
                                if (baseR instanceof WebPageR) {
                                    entry = createWebPage((WebPageR) baseR);

                                } else {
                                    entry = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return entry;
    }

    private void addAuthorsOrEditors(String person, ArrayList<String> listPeopleReference) {

        String[] authors = person.split(";");
        Collections.addAll(listPeopleReference, authors);
    }

    private void commonField(BaseR baseR, BaseReference entry) {
        entry.setNotes(baseR.getNote());
    }

    private JournalArticle createJournal(ArticleR reference) {

        JournalArticle journalArticle = new JournalArticle();
        commonField(reference, journalArticle);
        addAuthorsOrEditors(reference.getAuthor(), journalArticle.getAuthorList());
        journalArticle.setTitle(reference.getTitle());
        journalArticle.setJournal(reference.getJournal());
        journalArticle.setYear(reference.getYear());
        journalArticle.setVolume(reference.getVolume());
        journalArticle.setNumber(reference.getNumber());
        journalArticle.setPages(reference.getPages());
        journalArticle.setIssn(reference.getIssn());

        return journalArticle;
    }

    private Book createBook(BookR reference) {

        Book book = new Book();
        commonField(reference, book);
        addAuthorsOrEditors(reference.getAuthor(), book.getAuthorList());
        addAuthorsOrEditors(reference.getEditor(), book.getEditorList());
        book.setTitle(reference.getTitle());
        book.setPublisher(reference.getPublisher());
        book.setYear(reference.getYear());
        book.setVolume(reference.getVolume());
        book.setNumber(reference.getNumber());
        book.setSeries(reference.getSeries());
        book.setAddress(reference.getAddress());
        book.setEdition(reference.getEdition());
        book.setIsbn(reference.getIsbn());

        return book;
    }

    private BookSection createBookSection(BookSectionR reference) {

        BookSection section = new BookSection();
        commonField(reference, section);
        section.setChapter(reference.getChapter());
        section.setPages(reference.getPages());
        addAuthorsOrEditors(reference.getAuthor(), section.getAuthorList());
        addAuthorsOrEditors(reference.getEditor(), section.getEditorList());
        section.setTitle(reference.getTitle());
        section.setPublisher(reference.getPublisher());
        section.setYear(reference.getYear());
        section.setVolume(reference.getVolume());
        section.setNumber(reference.getNumber());
        section.setSeries(reference.getSeries());
        section.setAddress(reference.getAddress());
        section.setEdition(reference.getEdition());
        section.setIsbn(reference.getIsbn());

        return section;
    }

    private Thesis createThesis(ThesisR reference) {

        Thesis thesis = new Thesis();
        commonField(reference, thesis);
        addAuthorsOrEditors(reference.getAuthor(), thesis.getAuthorList());
        thesis.setTitle(reference.getTitle());
        thesis.setSchool(reference.getSchool());
        thesis.setYear(reference.getYear());
        if (reference.getType().equals(ThesisTypeLibrary.MASTERS)) {
            thesis.setType("Masters");
        } else {
            thesis.setType("phd");
        }
        thesis.setAddress(reference.getAddress());

        return thesis;
    }

    private ConferenceProceedings createConferenceProceedings(ConferenceProceedingsR reference) {

        ConferenceProceedings proceedings = new ConferenceProceedings();
        commonField(reference, proceedings);
        proceedings.setTitle(reference.getTitle());
        proceedings.setYear(reference.getYear());
        addAuthorsOrEditors(reference.getEditor(), proceedings.getEditorList());
        proceedings.setVolume(reference.getVolume());
        proceedings.setNumber(reference.getNumber());
        proceedings.setSeries(reference.getSeries());
        proceedings.setAddress(reference.getAddress());
        proceedings.setPublisher(reference.getPublisher());

        return proceedings;
    }

    private ConferencePaper createConferencePaper(ConferencePaperR reference) {

        ConferencePaper paper = new ConferencePaper();
        commonField(reference, paper);
        addAuthorsOrEditors(reference.getAuthor(), paper.getAuthorList());
        paper.setTitle(reference.getTitle());
        paper.setYear(reference.getYear());
        addAuthorsOrEditors(reference.getEditor(), paper.getEditorList());
        paper.setVolume(reference.getVolume());
        paper.setPages(reference.getPages());
        paper.setAddress(reference.getAddress());
        paper.setPublisher(reference.getPublisher());

        return paper;
    }

    private WebPage createWebPage(WebPageR reference) {

        WebPage webPage = new WebPage();
        commonField(reference, webPage);
        addAuthorsOrEditors(reference.getAuthor(), webPage.getAuthorList());
        webPage.setTitle(reference.getTitle());
        webPage.setYear(reference.getYear());
        webPage.setUrl(reference.getUrl());

        return webPage;
    }
}
