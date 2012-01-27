package org.bham.aucom.data.encoder;

import org.bham.aucom.Configuration;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Defines an abstract encoder. Encoders take system observations and turn them 
 * into a form that can be used by other parts of the system.
 * 
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public abstract class Encoder implements Serializable {

    private static final long serialVersionUID = 1L;
	private static Encoder instance;

    /**
     * Transform the {@link Observation} and turns it into a list of
     * {@link DomainFeature} objects.
     * 
     * @param in the observation
     * @return the list of domain features
     */
    public abstract List<DomainFeature> getFeatures(Observation in);

    /**
     * Create an integer representation of the {@link Observation}
     * 
     * @param in the observation
     * @return the integer encoding of the observation
     */
    public abstract int encode(Observation in);

    /**
     * Create an integer representation of the list of {@link DomainFeature} 
     * objects.
     * 
     * @param in domain feature list
     * @return the integer encoding
     */
    public abstract int encode(List<DomainFeature> in);

    /**
     * Decodes an integer to its associated DomainFeature list.
     * 
     * @param id encoded id
     * @return the list of domain features
     */
    public abstract List<DomainFeature> decode(int id);

    /**
     * Returns the whole set of encodings.
     * 
     * @return all encodings
     */
    public abstract ConcurrentHashMap<String, Integer> getEncoding();

    /**
     * Creates an instance of the encoder. The appropriate Encoder is dynamically
     * loaded at runtime.
     * 
     * @return an appropriate encoder
     */
    @SuppressWarnings("static-access")
    public static Encoder getInstance()
    {
    	if(instance == null){
            // TODO :: fix configuration path so encoder can load
    		String encoderClass = Configuration.getInstance().getValue("encoder");
    		if (encoderClass.isEmpty()){
    			return null;
    		}
    		try {
    			instance = (Encoder) Encoder.class.forName(encoderClass).newInstance();
    		} catch (Exception exception) {
    			instance = null;
    		}
    		
    	}
    	return instance;
    }
}
