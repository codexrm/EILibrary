package io.github.codexrm.EILibrary.controller;

import io.github.codexrm.EILibrary.enums.Format;
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


    public void exportReferenceList(Path path, ArrayList<Reference> referenceList, Format format) throws IOException {

        Export export = exportFactory.getExport(format);
        export.writeValue(referenceList, path.toString());
    }

    public ArrayList<Reference> importReferences(String path, Format format) throws IOException, TokenMgrException, ParseException {

        Import importer = importFactory.getImport(format);
        return importer.readFile(path);
    }
}
