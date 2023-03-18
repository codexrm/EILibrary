package io.github.codexrm.EILibrary.model;

import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import java.io.IOException;
import java.util.ArrayList;

public class ImportRis implements Import {


    @Override
    public ArrayList<Reference> readFile(String path) throws IOException, TokenMgrException, ParseException {
        return null;
    }
}
