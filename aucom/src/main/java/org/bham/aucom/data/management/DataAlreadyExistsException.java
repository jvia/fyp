package org.bham.aucom.data.management;

public class DataAlreadyExistsException extends Exception {
    
    private static final long serialVersionUID = 0L;
    
    public DataAlreadyExistsException() {
	super();
    }

    /**
     * Constructs a <code>DataAllreadyExistsException</code> with the
     * specified detail message. The string <code>s</code> can be
     * retrieved later by the
     * <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   s   the detail message.
     */
    public DataAlreadyExistsException(String s) {
	super(s);
    }
}
