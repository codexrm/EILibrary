package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.FormatLibrary;

public class ImportFactory {

    public ImportFactory() {
        // Do nothing
    }

    public Import getImport(FormatLibrary type) {

        if (type.equals(FormatLibrary.RIS)) {
            return  new ImportRis();
        } else {
            return new ImportBibTeX();
        }
    }
}
