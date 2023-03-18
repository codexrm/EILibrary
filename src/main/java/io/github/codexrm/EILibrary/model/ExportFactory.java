package io.github.codexrm.EILibrary.model;

import io.github.codexrm.EILibrary.enums.Format;

public class ExportFactory {

    public ExportFactory() {
        // Do nothing
    }

    public Export getExport(Format type) {

        if (type.equals(Format.RIS)) {
            return new ExportRis();
        } else {
            return new ExportBibTeX();
        }
    }
}
