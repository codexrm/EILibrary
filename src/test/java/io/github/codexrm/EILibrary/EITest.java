package io.github.codexrm.EILibrary;

import io.github.codexrm.EILibrary.controller.EIManager;
import io.github.codexrm.EILibrary.enums.*;
import io.github.codexrm.EILibrary.model.*;
import org.jbibtex.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class EITest {

    private final EIManager eiManager = new EIManager();

    @Test
    void ExportReference() {

        ArrayList<BaseR> baseRList = new ArrayList<>();

        baseRList.add(new ArticleR("Proyecto de medio ambiente", "2008", MonthsLibrary.mar , "aa", 1, "Medina,Juan", "ciencia y educacion", "1","3","10", "3842-4802"));

        baseRList.add(new BookR( "Relacion de las carreras", "2020--2021", MonthsLibrary.apr, "bb", 2, "Navarro,Enrique;Marin,Antonio", "Diaz,Mercedes", "Prencite Hall", "2", "First", "SLNS",
                "New York", "2", null));

        baseRList.add(new BookSectionR("La educacion secundaria", "2019", MonthsLibrary.nov, "cc", 3, "Soler,Marco", "Ulloa,Alicia", "K.madriz", "5", "1", "SDN", "DF,Mexico", "2",
                "3205-8104", "6", "30-50", BookSectionTypeLibrary.DataCD));

        baseRList.add(new BookLetR("Introduccion a las funciones", "2016", MonthsLibrary.dec, "dd", 4, "Fernandez,Julia","Inst. Ciencias", "Cienfuegos,Cuba"));

        baseRList.add(new ThesisR("Imagenes", "2023", MonthsLibrary.jun, "ee", 5, "Musa,Berta","Iberoamerica", ThesisTypeLibrary.PHD, "Quito,Ecuador"));

        baseRList.add(new ConferenceProceedingsR("Cambio Climatico", "2020", MonthsLibrary.may, "ff", 6, "Gomez,Claudia", "5", "3", "LTD","Bogota,Colombia", "Airt",
                "Lucha por el mundo", "3104-2018"));

        baseRList.add( new ConferencePaperR("Primefaces", "2012", MonthsLibrary.feb , "gg", 7, "Lopez,Beth", "Primefaces y otras tegnologias", "Guerrero,Olivia","3", "4", "DLS", "23,24",
                "Barcelona,España","Informatizacion", "Informatizacion"));

        baseRList.add(new WebPageR("Java FX", "2023", MonthsLibrary.aug, "hh", 8, "Alfonzo,Eric", "https://JavaFX"));

        Assertions.assertEquals(8, baseRList.size());

        try {
            final Path pathExportRis = Paths.get("testFile", "exportRis.txt");

            eiManager.exportReferenceList(pathExportRis, baseRList, FormatLibrary.RIS);

            final Path pathExportBibTeX = Paths.get("testFile", "exportBibTeX.txt");

            eiManager.exportReferenceList(pathExportBibTeX, baseRList, FormatLibrary.BIBTEX);

        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void ImportReference() throws IOException, ParseException {

        final Path pathImportRis = Paths.get("C:\\Users\\mary\\Desktop", "RIS.txt");

        ArrayList<BaseR> listRis = eiManager.importReferences(pathImportRis.toString(), FormatLibrary.RIS);

        Assertions.assertEquals(7, listRis.size());


        final Path pathImportBibTeX = Paths.get("testFile", "exportBibTeX.txt");

        ArrayList<BaseR> listBibTeX = eiManager.importReferences(pathImportBibTeX.toString(), FormatLibrary.BIBTEX);

        Assertions.assertEquals(7, listBibTeX.size());

        }
    }






