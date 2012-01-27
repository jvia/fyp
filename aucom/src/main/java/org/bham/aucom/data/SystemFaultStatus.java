package org.bham.aucom.data;

/**
 * An enum for signaling the status of the system.
 * 
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public enum SystemFaultStatus {

    /**
     * The system status is unknown.
     */
    UNKNOWN,
    /**
     * The system is running normally.
     */
    NORMAL,
    /**
     * A fault has been detected in the system.
     */
    ABNORMAL
}
