package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.AttributableObject;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassificatorListener;
import org.bham.aucom.data.timeseries.AnomalyClassificatorStatusEvent;

import java.io.Serializable;
import java.util.UUID;

/**
 * An interface which allows different kinds of classifiers to be used
 * interchangeably.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
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
    public void setClassifier(AnomalyClassifier classifier) throws ClassCastException;

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
