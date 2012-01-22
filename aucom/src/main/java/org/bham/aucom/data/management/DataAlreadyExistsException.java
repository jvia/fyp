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

    /*
     * Constructs a <code>DataAllreadyExistsException</code> with a detail message
     * consisting of the given pathname string followed by the given reason
     * string.  If the <code>reason</code> argument is <code>null</code> then
     * it will be omitted.  This private constructor is invoked only by native
     * I/O methods.
     *
     * @since 1.2
     */
    private DataAlreadyExistsException(String path, String reason) {
	super(path + ((reason == null)
		      ? ""
		      : " (" + reason + ")"));
    }
}
