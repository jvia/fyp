package org.bham.aucom;

/**
 * An exception thrown when an illegal action is attempted.
 */
public class ActionNotPermittedException extends Exception {

    private static final long serialVersionUID = -3483908239895390408L;

    /**
     * Create the exception.
     *
     * @param msg error message
     */
    public ActionNotPermittedException(String msg) {
        super(msg);
    }
}
