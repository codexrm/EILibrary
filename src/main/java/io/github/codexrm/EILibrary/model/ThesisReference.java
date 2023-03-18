package io.github.codexrm.EILibrary.model;

public class ThesisReference extends Reference {

    private String author;
    private String school;
    private String type;
    private String address;

    public ThesisReference() {
    }

    public ThesisReference(String title, String year, String month, String note, String author, String school, String type, String address) {
        super(title, year, month, note);
        this.author = author;
        this.school = school;
        this.type = type;
        this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }
}
