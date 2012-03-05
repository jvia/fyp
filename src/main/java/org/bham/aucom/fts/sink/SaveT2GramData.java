package org.bham.aucom.fts.sink;

import net.sf.xcf.fts.nodes.sink.SinkAdapter;
import org.bham.aucom.data.DataType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SaveT2GramData extends SinkAdapter<DataType> {
    FileOutputStream out;

    public SaveT2GramData() {
        try {
            out = new FileOutputStream(new File("t2gram.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void pushItem(DataType data) throws Exception {

    }

}