package org.bham.app.experiment;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class EmptyResult extends Result {
    @Override
    public String getAsCsvString() {
        return "";
    }
}
