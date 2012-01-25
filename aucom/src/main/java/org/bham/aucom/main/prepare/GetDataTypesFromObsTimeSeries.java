package org.bham.aucom.main.prepare;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class GetDataTypesFromObsTimeSeries {

    private GetDataTypesFromObsTimeSeries() {
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            List<File> files = new LinkedList<File>();
            for (String arg : args) {
                files.add(new File(arg));
            }
            new GetDataTypesFromObsTimeSeries();
        }
    }

}
