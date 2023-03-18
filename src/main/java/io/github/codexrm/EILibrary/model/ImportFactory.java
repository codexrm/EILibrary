package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Format;

public class ImportFactory {

    public ImportFactory() {
        // Do nothing
    }

    public Import getImport(Format type) {

        if (type.equals(Format.RIS)) {
            return  new ImportRis();
        } else {
            return new ImportBibTeX();
        }
    }
}
