package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.FormatLibrary;

public class ExportFactory {

    public ExportFactory() {
        // Do nothing
    }

    public Export getExport(FormatLibrary type) {

        if (type.equals(FormatLibrary.RIS)) {
            return new ExportRis();
        } else {
            return new ExportBibTeX();
        }
    }
}
