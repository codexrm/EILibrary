package io.github.codexrm.EILibrary.controller;

import io.github.codexrm.EILibrary.enums.Format;
import io.github.codexrm.EILibrary.model.*;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EIManager {

    private ArrayList<Reference> referenceList;
    private ExportFactory exportFactory;
    private ImportFactory importFactory;

    public EIManager() {
        referenceList = new ArrayList<>();
        exportFactory = new ExportFactory();
        importFactory = new ImportFactory();
    }

    public ArrayList<Reference> getReferenceList() { return referenceList; }

    public void setReferenceList(ArrayList<Reference> referenceList) { this.referenceList = referenceList; }

    public ExportFactory getExportFactory() { return exportFactory; }

    public void setExportFactory(ExportFactory exportFactory) { this.exportFactory = exportFactory; }

    public ImportFactory getImportFactory() { return importFactory; }

    public void setImportFactory(ImportFactory importFactory) { this.importFactory = importFactory; }


    public void exportReferenceList(File file, ArrayList<Reference> referenceList, Format format) throws IOException {

        Export export = exportFactory.getExport(format);
        export.writeValue(referenceList, file.getPath());
    }

    public ArrayList<Reference> importReferences(String path, Format format) throws IOException, TokenMgrException, ParseException {

        Import importer = importFactory.getImport(format);
        return importer.readFile(path);
    }
}
