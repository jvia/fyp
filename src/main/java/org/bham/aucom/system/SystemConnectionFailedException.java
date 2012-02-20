package org.bham.aucom.system;

public class SystemConnectionFailedException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String reason;

    public SystemConnectionFailedException() {
        reason = "";
    }

    public SystemConnectionFailedException(String inReason) {
        reason = inReason;
    }

    public String getReason() {
        return reason;
    }

}
