package org.bham.aucom.fts.tranform;

import java.util.List;
import java.util.logging.Logger;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.encoder.Encoder;


public class EncodeData extends AbstractAucomTransformNode<Observation, DataType> {
    private Encoder encoder;

    public EncodeData() {
        super("EncodeData");
        this.setEncoder(Encoder.getInstance());
    }

    @Override
    public DataType iTransform(Observation input) throws Exception
    {
        try {
            Logger.getLogger(this.getClass().getCanonicalName()).info("encoding " + input.toString());
            int dataType = getEncoder().encode(input);
            List<DomainFeature> features = getEncoder().getFeatures(input);
            if (features == null) {
                System.err.println("features are null, cannot return proper DataType");
                return null;
            }
            DataType type = new DataType(features, dataType, input);
            type.setEventTypeId(dataType);
            return type;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    void setEncoder(Encoder encoder)
    {
        this.encoder = encoder;
    }

    Encoder getEncoder()
    {
        return this.encoder;
    }

}
