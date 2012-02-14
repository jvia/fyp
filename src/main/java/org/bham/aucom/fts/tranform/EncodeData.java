package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.encoder.Encoder;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This node accepts event input and determines its encoding. The file which
 * contains encoding data is called 'classes.txt'. This encoding is used as a
 * shorthand reference to refer to the event type throughout the system.
 * <p/>
 * An example from the 'classes.txt' file where input from CAST is encoded to
 * 1:
 * <p/>
 * -> ADD:node0:three.sa => 1
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class EncodeData extends AbstractAucomTranformNode<Observation, DataType> {
    private Encoder encoder;
    private final transient Logger log = Logger.getLogger(getClass().getName());

    /**
     * Create the encoder node with the specific encoder type. This encoder
     * information is read from file and must be configured before running the
     * system.
     */
    public EncodeData() {
        // TODO :: refactor this so that the encoder is constructor parameter
        super("EncodeData");
        setEncoder(Encoder.getInstance());
    }

    /**
     * Takes system input and returns the correct encoding. If it receives
     * input
     * which is not represented in the encoding file, then is returns null.
     *
     * @param input system event
     * @return the encoding if one exists, otherwise null
     * @throws Exception something went wrong
     */
    @Override
    public DataType iTransform(Observation input) throws Exception {
        log.info("Encoding " + input.toString());

        try {
            int dataType = getEncoder().encode(input);
            List<DomainFeature> features = getEncoder().getFeatures(input);
            return new DataType(features, dataType, input);
        } catch (Exception exception) {
            log.log(Level.SEVERE, "Features are null. Cannot return proper DataType.", exception);
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Set the encoder type.
     *
     * @param encoder the encoder
     */
    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Get the encoder.
     *
     * @return the encoder
     */
    public Encoder getEncoder() {
        return this.encoder;
    }
}
