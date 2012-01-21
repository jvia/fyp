package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import java.io.Serializable;
import java.util.UUID;

import org.bham.aucom.data.AttributableObject;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassificatorListener;
import org.bham.aucom.data.timeseries.AnomalyClassificatorStatusEvent;


/**
 * An interface which allows different kinds of classifiers to be used 
 * interchangeably.
 * 
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public interface AnomalyClassificator extends AttributableObject, Serializable {

    /**
     * Return the unique ID of this object.
     * 
     * @return the object ID
     */
    public UUID getId();

    /**
     * Determines if the Score satisfies some criteria.
     * 
     * @param s the score
     * @return true if criteria are satisfied; false otherwise
     * @see Score
     */
    public boolean satisfies(Score s);

    /**
     * Sets the type of classifier to be used.
     * 
     * @param classificator the classified to be used
     * @throws ClassCastException invalid classifier type
     */
    public void setClassificator(AnomalyClassificator classificator) throws ClassCastException;

    /**
     * Makes this object a copy of the object passed in.
     * 
     * @param classificator the object to copy
     */
    public void copy(AnomalyClassificator classificator);

    /**
     * Get a copy of the classifier.
     * 
     * @return a copy of this classifier
     */
    public AnomalyClassificator duplicate();

    /**
     * Adds a listener object which can handle 
     * {@link AnomalyClassificatorStatusEvent} changes.
     * 
     * @param listener the listener
     */
    public void addSequenceStatusListener(AnomalyClassificatorListener listener);

    /**
     * Removes the listener that can handle {@link AnomalyClassificatorStatusEvent}
     * events.
     * 
     * @param listener the listener to remove
     */
    public void removeStatusListener(AnomalyClassificatorListener listener);
    
    /**
     * Can be implemented to reset any necessary interior state variables.
     */
    
    public void reset();
}
