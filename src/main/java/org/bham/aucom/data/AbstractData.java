package org.bham.aucom.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public abstract class AbstractData implements AttributableObject {

    /**
     * The map of attributes about a piece of data.
     */
    protected Map<String, String> attributes;
    private boolean isFirstElement;
    private boolean isLastElement;
    private long timestamp;

    /**
     * Creates AbstractData using default (read non-valid) data.
     */
    public AbstractData() {
        this(false, false, -1);
    }

    /**
     * Creates an AbstractData object.
     *
     * @param isFirstElement is this the first element in the set
     * @param isLastElement  is this the last element in the set
     * @param timestamp      the time associated with this object
     */
    public AbstractData(final boolean isFirstElement,
                        final boolean isLastElement,
                        final long timestamp) {
        this.isFirstElement = isFirstElement;
        this.isLastElement = isLastElement;
        this.timestamp = timestamp;
        attributes = new LinkedHashMap<String, String>();
    }

    /**
     * Return a copy of this object.
     *
     * @return  a copy of the object
     */
    public abstract Object copy();

    /**
     * Return the collection of attributes that describe an object.
     *
     * @return collection of attributes
     */
    @Override
    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    /**
     * Add an attribute about the object.
     *
     * @param propertyName  name of the attribute
     * @param propertyValue value of the attribute
     */
    @Override
    public void addAttribute(final String propertyName,
                             final String propertyValue) {
        attributes.put(propertyName, propertyValue);
    }

    /**
     * Remove an attribute of an object.
     *
     * @param propertyName name of the attribute
     */
    @Override
    public void deleteAttribute(final String propertyName) {
        if (this.containsAttribute(propertyName)) {
            attributes.remove(propertyName);
        }
    }

    /**
     * Get the value associated with an attribute name.
     *
     * @param propertyName name of the attribute
     * @return the value of the attribute
     */
    @Override
    public String getAttributeValue(final String propertyName) {
        if (!containsAttribute(propertyName)) {
            return null;
        }
        return this.attributes.get(propertyName);
    }

    /**
     * Determines if the object contains the specified attribute.
     *
     * @param propertyName name of the attribute
     * @return true if the attribute exists, false otherwise
     */
    @Override
    public boolean containsAttribute(final String propertyName) {
        return attributes.containsKey(propertyName);
    }

    /**
     * Get the object's timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Set the timestamp
     *
     * @param inTimestamp the timestamp in milliseconds
     */
    public void setTimestamp(final long inTimestamp) {
        timestamp = inTimestamp;
    }

    /**
     * Change the object's first element status.
     *
     * @param isFirstElement true if first; false otherwise
     */
    public void setFirstElement(boolean isFirstElement) {
        this.isFirstElement = isFirstElement;
    }

    /**
     * Determine if the object is the first element.
     *
     * @return true if th object is the first element; false otherwise
     */
    public boolean isFirstElement() {
        return isFirstElement;
    }

    /**
     * Change the object's last element status.
     *
     * @param isLastElement true if last; false otherwise
     */
    public void setLastElement(final boolean isLastElement) {
        this.isLastElement = isLastElement;
    }

    /**
     * Determine if the object is the last element.
     *
     * @return true if th object is the last element; false otherwise
     */
    public boolean isLastElement() {
        return isLastElement;
    }
}