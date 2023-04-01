package io.github.codexrm.EILibrary.enums;

public enum ThesisTypeLibrary {

    MASTERS("Masters"),
    PHD("phd");

    private final String description;

    ThesisTypeLibrary(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}