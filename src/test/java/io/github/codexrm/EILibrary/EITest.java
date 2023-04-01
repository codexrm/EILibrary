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

        baseRList.add(new ArticleR("Proyecto de medio ambiente", "2008", Months.mar , "aa", 1, "Medina,Juan", "ciencia y educacion", "1","3","10", "3842-4802"));

        baseRList.add(new BookR( "Relacion de las carreras", "2020--2021", Months.apr, "bb", 2, "Navarro,Enrique;Marin,Antonio", "Diaz,Mercedes", "Prencite Hall", "2", "First", "SLNS",
                "New York", "2", "5510-2031"));

        baseRList.add(new BookSectionR("La educacion secundaria", "2019", Months.nov, "cc", 3, "Soler,Marco", "Ulloa,Alicia", "K.madriz", "5", "1", "SDN", "DF,Mexico", "2",
                "3205-8104", "6", "30-50", BookSectionType.DataCD));

        baseRList.add(new BookLetR("Introduccion a las funciones", "2016", Months.dec, "dd", 4, "Fernandez,Julia","Inst. Ciencias", "Cienfuegos,Cuba"));

        baseRList.add(new ThesisR("Imagenes", "2023", Months.jun, "ee", 5, "Musa,Berta","Iberoamerica", ThesisType.PHD, "Quito,Ecuador"));

        baseRList.add(new ConferenceProceedingsR("Cambio Climatico", "2020", Months.may, "ff", 6, "Gomez,Claudia", "5", "3", "LTD","Bogota,Colombia", "Airt",
                "Lucha por el mundo", "3104-2018"));

        baseRList.add( new ConferencePaperR("Primefaces", "2012", Months.feb , "gg", 7, "Lopez,Beth", "Primefaces y otras tegnologias", "Guerrero,Olivia","3", "4", "DLS", "23,24",
                "Barcelona,España","Informatizacion", "Informatizacion"));

        baseRList.add(new WebPageR("Java FX", "2023", Months.aug, "hh", 8, "Alfonzo,Eric", "https://JavaFX"));

        Assertions.assertEquals(8, baseRList.size());

        try {
            final Path pathExportRis = Paths.get("testFile", "exportRis.txt");

            eiManager.exportReferenceList(pathExportRis, baseRList, Format.RIS);

            final Path pathExportBibTeX = Paths.get("testFile", "exportBibTeX.txt");

            eiManager.exportReferenceList(pathExportBibTeX, baseRList, Format.BIBTEX);

        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void ImportReference() throws IOException, ParseException {

        final Path pathImportRis = Paths.get("testFile", "importRis.txt");

        ArrayList<BaseR> listRis = eiManager.importReferences(pathImportRis.toString(), Format.RIS);

        Assertions.assertEquals(7, listRis.size());


        final Path pathImportBibTeX = Paths.get("testFile", "exportBibTeX.txt");

        ArrayList<BaseR> listBibTeX = eiManager.importReferences(pathImportBibTeX.toString(), Format.BIBTEX);

        Assertions.assertEquals(7, listBibTeX.size());

        }
    }






