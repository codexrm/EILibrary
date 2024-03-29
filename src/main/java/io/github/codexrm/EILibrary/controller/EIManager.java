package io.github.codexrm.EILibrary.controller;

import io.github.codexrm.EILibrary.enums.FormatLibrary;
import io.github.codexrm.EILibrary.model.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class EIManager {

    private ExportFactory exportFactory;
    private ImportFactory importFactory;

    public EIManager() {
        exportFactory = new ExportFactory();
        importFactory = new ImportFactory();
    }

    public ExportFactory getExportFactory() { return exportFactory; }

    public void setExportFactory(ExportFactory exportFactory) { this.exportFactory = exportFactory; }

    public ImportFactory getImportFactory() { return importFactory; }

    public void setImportFactory(ImportFactory importFactory) { this.importFactory = importFactory; }

    public void exportReferenceList(Path path, ArrayList<BaseR> baseRList, FormatLibrary format) throws IOException {

        Export export = exportFactory.getExport(format);
        export.writeValue(baseRList, path.toString());
    }

    public ArrayList<BaseR> importReferences(String path, FormatLibrary format) throws IOException, TokenMgrException, ParseException {

        Import importer = importFactory.getImport(format);
        return importer.readFile(path);
    }
}
