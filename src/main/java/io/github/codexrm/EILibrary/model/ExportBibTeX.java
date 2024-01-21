package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.ThesisTypeLibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExportBibTeX implements Export {

    private final String vl = "  volume = {";
    private final String ad = "  address = {";

    @Override
    public void writeValue(ArrayList<BaseR> baseRList, String path) throws IOException {

        for (BaseR baseR : baseRList) {
            identifyType(baseR, path);
        }
    }

    private void identifyType(BaseR baseR, String path) throws IOException {

        FileWriter writer = new FileWriter(path, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        if (baseR instanceof ArticleR) {
            writeArticleReference((ArticleR) baseR, bufferedWriter);

        } else {
            if (baseR instanceof BookSectionR) {
                writeBookSectionReference((BookSectionR) baseR, bufferedWriter);

            } else {
                if (baseR instanceof BookR) {
                    writeBookReference((BookR) baseR, bufferedWriter);

                } else {
                    if (baseR instanceof ThesisR) {
                        writeThesisReference((ThesisR) baseR, bufferedWriter);

                    } else {
                        if (baseR instanceof BookLetR) {
                            writeBookLetReference((BookLetR) baseR, bufferedWriter);

                        } else {
                            if (baseR instanceof ConferenceProceedingsR) {
                                writeConferenceProceedingsReference((ConferenceProceedingsR) baseR,
                                        bufferedWriter);
                            } else {
                                if (baseR instanceof ConferencePaperR) {
                                    writeConferencePaperReference((ConferencePaperR) baseR,
                                            bufferedWriter);
                                } else {
                                    if (baseR instanceof WebPageR) {
                                        writeWebPageReference((WebPageR) baseR, bufferedWriter);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void writeAuthors(String author, BufferedWriter bufferedWriter)
            throws IOException {

        if (author != null) {
            bufferedWriter.write("  author = {");
            String[] authors = author.split(";");
            for (int i = 0; i < authors.length; i++) {

                if (i == 0) {
                    bufferedWriter.write(authors[i]);
                } else {
                    bufferedWriter.write(" and ");
                    bufferedWriter.write(authors[i]);
                }
            }
            bufferedWriter.write("},");
            bufferedWriter.newLine();
        }
    }

    private void writeEditors(String editor, BufferedWriter bufferedWriter)
            throws IOException {

        if (editor != null) {
            bufferedWriter.write("  editor = {");
            String[] authors = editor.split(";");
            for (int i = 0; i < authors.length; i++) {

                if (i == 0) {
                    bufferedWriter.write(authors[i]);
                } else {
                    bufferedWriter.write(" and ");
                    bufferedWriter.write(authors[i]);
                }
            }
            bufferedWriter.write("},");
            bufferedWriter.newLine();
        }
    }



    private void commonField(BaseR baseR, BufferedWriter bufferedWriter) throws IOException {

         bufferedWriter.newLine();

        if (baseR.getTitle() != null) {
            bufferedWriter.write("  title = {" + baseR.getTitle() + "},");
            bufferedWriter.newLine();
        }
        if (baseR.getMonth() != null) {
            bufferedWriter.write("  month = " + baseR.getMonth() + ",");
            bufferedWriter.newLine();
        }
        if(baseR.getYear() != null){
            bufferedWriter.write("  year = {" + baseR.getYear() + "},");
            bufferedWriter.newLine();
        }
        if (baseR.getNote() != null) {
            bufferedWriter.write("  note = {" + baseR.getNote() + "},");
            bufferedWriter.newLine();
        }
    }

    private void closeReference(BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.newLine();
        bufferedWriter.write("}");
        bufferedWriter.newLine();
        bufferedWriter.write("");
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    private void writeArticleReference(ArticleR reference, BufferedWriter bufferedWriter)
            throws IOException {

        bufferedWriter.write("@article{" + reference.getId() + ",");
        commonField(reference, bufferedWriter);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),bufferedWriter);
        }
        if (reference.getJournal() != null) {
            bufferedWriter.write("  journal = {" + reference.getJournal() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getVolume() != null) {
            bufferedWriter.write(vl + reference.getVolume() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getNumber() != null) {
            bufferedWriter.write("  number = {" + reference.getNumber() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getPages() != null) {
            bufferedWriter.write("  pages = {" + reference.getPages() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getIssn() != null) {
            bufferedWriter.write("  issn = {" + reference.getIssn() + "}");
        }

        closeReference(bufferedWriter);
    }

    private void writeBooks(BookR reference, BufferedWriter bufferedWriter) throws IOException {

        commonField(reference, bufferedWriter);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),bufferedWriter);
        }
        if (reference.getEditor() != null) {
            writeEditors(reference.getEditor(),bufferedWriter);
        }
        if (reference.getPublisher() != null) {
            bufferedWriter.write("  publisher = {" + reference.getPublisher() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getVolume() != null) {
            bufferedWriter.write(vl + reference.getVolume() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getNumber() != null) {
            bufferedWriter.write("  number = {" + reference.getNumber() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getSeries() != null) {
            bufferedWriter.write("  series = {" + reference.getSeries() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getAddress() != null) {
            bufferedWriter.write(ad + reference.getAddress() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getEdition() != null) {
            bufferedWriter.write("  edition = {" + reference.getEdition() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getIsbn() != null) {
            bufferedWriter.write("  isbn = {" + reference.getIsbn() + "}");
        }
    }

    private void writeBookReference(BookR reference, BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("@book{" + reference.getId() + ",");
        writeBooks(reference, bufferedWriter);
        closeReference(bufferedWriter);
    }

    private void writeBookSectionReference(BookSectionR reference,
                                           BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("@inbook{" + reference.getId() + ",");

        if (reference.getChapter() != null) {
            bufferedWriter.newLine();
            bufferedWriter.write("  chapter = {" + reference.getChapter() + "},");
        }
        if (reference.getPages() != null) {
            bufferedWriter.newLine();
            bufferedWriter.write("  pages = {" + reference.getPages() + "},");
        }
        if (reference.getType() != null) {
            bufferedWriter.newLine();
            bufferedWriter.write("  type = {" + reference.getType().toString() + "},");
        }

        writeBooks(reference, bufferedWriter);
        closeReference(bufferedWriter);
    }

    private void writeBookLetReference(BookLetR reference, BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("@booklet{" + reference.getId() + ",");
        commonField(reference, bufferedWriter);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),bufferedWriter);
        }
        if (reference.getHowpublished() != null) {
            bufferedWriter.write("  howpublished = {" + reference.getHowpublished() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getAddress() != null) {
            bufferedWriter.write(ad + reference.getAddress() + "}");
        }
        closeReference(bufferedWriter);
    }

    private void writeThesisReference(ThesisR reference, BufferedWriter bufferedWriter) throws IOException {

        if (reference.getType().equals(ThesisTypeLibrary.MASTERS)) {
            bufferedWriter.write("@mastersthesis{" + reference.getId() + ",");
        } else {
            bufferedWriter.write("@phdthesis{" + reference.getId() + ",");
        }
        commonField(reference, bufferedWriter);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),bufferedWriter);
        }
        if (reference.getSchool() != null) {
            bufferedWriter.write("  school = {" + reference.getSchool() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getType() != null) {
            bufferedWriter.write("  type = {" + reference.getType() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getAddress() != null) {
            bufferedWriter.write(ad + reference.getAddress() + "}");
        }
        closeReference(bufferedWriter);
    }

    private void writeConferenceProceedingsReference(ConferenceProceedingsR reference, BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("@proceedings{" + reference.getId() + ",");
        commonField(reference, bufferedWriter);

        if (reference.getEditor() != null) {
            writeEditors(reference.getEditor(),bufferedWriter);
        }
        if (reference.getVolume() != null) {
            bufferedWriter.write(vl + reference.getVolume() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getNumber() != null) {
            bufferedWriter.write("  number = {" + reference.getNumber() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getSeries() != null) {
            bufferedWriter.write("  series = {" + reference.getSeries() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getPublisher() != null) {
            bufferedWriter.write("  publisher = {" + reference.getPublisher() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getOrganization() != null) {
            bufferedWriter.write("  organization = {" + reference.getOrganization() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getIsbn() != null) {
            bufferedWriter.write("  isbn = {" + reference.getIsbn() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getAddress() != null) {
            bufferedWriter.write(ad + reference.getAddress() + "}");
        }
        closeReference(bufferedWriter);
    }

    private void writeConferencePaperReference(ConferencePaperR reference, BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("@InProceedings{" + reference.getId() + ",");
        commonField(reference, bufferedWriter);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),bufferedWriter);
        }
        if (reference.getEditor() != null) {
            writeEditors(reference.getEditor(),bufferedWriter);
        }
        if (reference.getBookTitle() != null) {
            bufferedWriter.write("  booktitle = {" + reference.getBookTitle() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getVolume() != null) {
            bufferedWriter.write(vl + reference.getVolume() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getNumber() != null) {
            bufferedWriter.write("  number = {" + reference.getNumber() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getSeries() != null) {
            bufferedWriter.write("  series = {" + reference.getSeries() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getPublisher() != null) {
            bufferedWriter.write("  publisher = {" + reference.getPublisher() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getOrganization() != null) {
            bufferedWriter.write("  organization = {" + reference.getOrganization() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getAddress() != null) {
            bufferedWriter.write(ad + reference.getAddress() + "},");
            bufferedWriter.newLine();
        }
        if (reference.getPages() != null) {
            bufferedWriter.write("  pages = {" + reference.getPages() + "}");
        }
        closeReference(bufferedWriter);
    }

    private void writeWebPageReference(WebPageR reference, BufferedWriter bufferedWriter) throws IOException {

        bufferedWriter.write("@misc{" + reference.getId() + ",");
        commonField(reference, bufferedWriter);

        if (reference.getAuthor() != null) {
            writeAuthors(reference.getAuthor(),bufferedWriter);
        }
        if (reference.getUrl() != null) {
            bufferedWriter.write("  howpublished = {" + reference.getUrl() + "}");
        }
        closeReference(bufferedWriter);
    }
}
