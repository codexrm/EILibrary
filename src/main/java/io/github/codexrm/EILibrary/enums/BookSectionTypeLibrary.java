package io.github.codexrm.EILibrary.enums;

public enum BookSectionTypeLibrary {

    MATHESIS("Master´s thesis"),
    PHDTHESIS("PhD thesis"),
    CANDTHESIS("Candidate thesis"),
    TECHREPORT("Technical report"),
    RESREPORT("Research report"),
    SOFTWARE("Software"),
    AUDIOCD("Audio CD"),
    DataCD("Data CD");

    private final String description;

    BookSectionTypeLibrary(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
