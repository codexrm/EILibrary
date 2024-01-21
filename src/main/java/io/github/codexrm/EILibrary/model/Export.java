package io.github.codexrm.EILibrary.model;

import java.io.IOException;
import java.util.ArrayList;

public interface Export {

    void writeValue(ArrayList<BaseR> baseRList, String path) throws IOException;
}
