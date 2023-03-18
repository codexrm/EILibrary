package io.github.codexrm.EILibrary.model;

import java.io.IOException;
import java.util.ArrayList;

public interface Export {

    void writeValue(ArrayList<Reference> referenceList, String path) throws IOException;
}
