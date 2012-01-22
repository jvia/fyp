package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import java.io.Serializable;
import java.util.UUID;

import org.bham.aucom.data.AttributableObject;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassifierListener;


/**
 * An interface which allows different kinds of classifiers to be used 
 * interchangeably.
 * 
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public interface AnomalyClassifier extends AttributableObject, Serializable {

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
     * @param classifier the classified to be used
     * @throws ClassCastException invalid classifier type
     */
    public void setClassificator(AnomalyClassifier classifier) throws ClassCastException;

    /**
     * Makes this object a copy of the object passed in.
     * 
     * @param classifier the object to copy
     */
    public void copy(AnomalyClassifier classifier);

    /**
     * Get a copy of the classifier.
     * 
     * @return a copy of this classifier
     */
    public AnomalyClassifier duplicate();

    /**
     * Adds a listener object which can handle 
     * {@link org.bham.aucom.data.timeseries.AnomalyClassifierStatusEvent} changes.
     * 
     * @param listener the listener
     */
    public void addSequenceStatusListener(AnomalyClassifierListener listener);

    /**
     * Removes the listener that can handle {@link org.bham.aucom.data.timeseries.AnomalyClassifierStatusEvent}
     * events.
     * 
     * @param listener the listener to remove
     */
    public void removeStatusListener(AnomalyClassifierListener listener);
    
    /**
     * Can be implemented to reset any necessary interior state variables.
     */
    
    public void reset();
}
