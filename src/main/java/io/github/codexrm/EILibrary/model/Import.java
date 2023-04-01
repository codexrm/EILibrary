package io.github.codexrm.EILibrary.model;

import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

import java.io.IOException;
import java.util.ArrayList;

public interface Import {

    ArrayList<BaseR> readFile(String path) throws IOException, TokenMgrException, ParseException;
}
