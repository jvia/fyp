package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;

import java.util.logging.Logger;

public class T2GramDataToString extends AbstractTransformNode<Score, String> {
    @Override
    protected String transform(Score arg0) throws Exception {
        String out = str(arg0);
        Logger.getLogger(this.getClass().getCanonicalName()).info(out);
        return out;
    }

    private String str(Score arg0) {
        /*
           * timestamp, classid, classidPrecedessor1,DurationPrecedessor1,ProbabilityPrecedessor1, ClassIdPrecedessor2, ....
           */
        String out = "";
        out += String.valueOf(arg0.getTimestamp());
        out += ";";
        out += String.valueOf(arg0.getEventType());
        out += ";";
        out += String.valueOf(arg0.getValue());
        out += ";";
        for (DataType precedessorDataType : arg0.getPredecessors()) {
            out += String.valueOf(precedessorDataType);
            out += ";";
            out += String.valueOf(arg0.getDurationFor(precedessorDataType));
            out += ";";
            out += String.valueOf(arg0.getProbabilityFor(precedessorDataType));
            out += ";";
        }
        return out + "\n";
    }

}
