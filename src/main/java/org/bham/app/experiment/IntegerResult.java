package org.bham.app.experiment;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class IntegerResult extends Result {

    private final int result;

    public IntegerResult(final int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String getAsCsvString() {
        return String.valueOf(result);
    }
}
