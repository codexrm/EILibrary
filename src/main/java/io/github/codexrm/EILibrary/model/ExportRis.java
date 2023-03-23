package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.ThesisType;
import io.github.codexrm.jris.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ExportRis implements Export {


    @Override
    public void writeValue(ArrayList<Reference> referenceList, String path) throws IOException {

        RisManager manager = new RisManager();
        for (Reference reference : referenceList) {
            BaseReference entry = identifyType(reference);
            if (entry != null) {
                manager.addReference(entry);
            }
        }
        manager.exportListReference(path);
    }

    private BaseReference identifyType(Reference reference) {

        BaseReference entry;

        if (reference instanceof ArticleReference) {
            entry = createJournal((ArticleReference) reference);

        } else {
            if (reference instanceof BookSectionReference) {
                entry = createBookSection((BookSectionReference) reference);

            } else {
                if (reference instanceof BookReference) {
                    entry = createBook((BookReference) reference);

                } else {
                    if (reference instanceof ThesisReference) {
                        entry = createThesis((ThesisReference) reference);

                    } else {
                        if (reference instanceof ConferenceProceedingsReference) {
                            entry = createConferenceProceedings((ConferenceProceedingsReference) reference);

                        } else {
                            if (reference instanceof ConferencePaperReference) {
                                entry = createConferencePaper((ConferencePaperReference) reference);

                            } else {
                                if (reference instanceof WebPageReference) {
                                    entry = createWebPage((WebPageReference) reference);

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

    private void commonField(Reference reference, BaseReference entry) {
        entry.setNotes(reference.getNote());
    }

    private JournalArticle createJournal(ArticleReference reference) {

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

    private Book createBook(BookReference reference) {

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

    private BookSection createBookSection(BookSectionReference reference) {

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

    private Thesis createThesis(ThesisReference reference) {

        Thesis thesis = new Thesis();
        commonField(reference, thesis);
        addAuthorsOrEditors(reference.getAuthor(), thesis.getAuthorList());
        thesis.setTitle(reference.getTitle());
        thesis.setSchool(reference.getSchool());
        thesis.setYear(reference.getYear());
        if (reference.getType().equals(ThesisType.MASTERS)) {
            thesis.setType("Masters");
        } else {
            thesis.setType("phd");
        }
        thesis.setAddress(reference.getAddress());

        return thesis;
    }

    private ConferenceProceedings createConferenceProceedings(ConferenceProceedingsReference reference) {

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

    private ConferencePaper createConferencePaper(ConferencePaperReference reference) {

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

    private WebPage createWebPage(WebPageReference reference) {

        WebPage webPage = new WebPage();
        commonField(reference, webPage);
        addAuthorsOrEditors(reference.getAuthor(), webPage.getAuthorList());
        webPage.setTitle(reference.getTitle());
        webPage.setYear(reference.getYear());
        webPage.setUrl(reference.getUrl());

        return webPage;
    }
}
