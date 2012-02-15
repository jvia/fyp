package org.bham.aucom.data;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public abstract class AbstractData implements AttributableObject {

    // TODO: make  type map
    /**
     * The list of attributes about a piece of data.
     */
    protected HashMap<String, String> attributes;
    private boolean isFirstElement;
    private boolean isLastElement;
    private long timestamp;

    /**
     * Creates AbstractData using default (read non-valid) data.
     */
    public AbstractData()
    {
        this(false, false, -1);
    }

    /**
     * Creates an AbstractData object.
     * 
     * @param isFirstElement is this the first element in the set
     * @param isLastElement is this the last element in the set
     * @param timestamp the time associated with this object
     */
    public AbstractData(boolean isFirstElement, boolean isLastElement, long timestamp)
    {
        this.isFirstElement = isFirstElement;
        this.isLastElement = isLastElement;
        this.timestamp = timestamp;
        attributes = new LinkedHashMap<String, String>();
    }

    /**
     * Return the collection of attributes that describe an object.
     * 
     * @return collection of attributes
     */
    @Override
    public HashMap<String, String> getAttributes()
    {
        return this.attributes;
    }

    /**
     * Add an attribute about the object.
     * 
     * @param propertyName name of the attribute
     * @param propertyValue value of the attribute
     */
    @Override
    public void addAttribute(String propertyName, String propertyValue)
    {
        this.attributes.put(propertyName, propertyValue);
    }

    /**
     * Remove an attribute of an object.
     * 
     * @param propertyName name of the attribute
     */
    @Override
    public void deleteAttribute(String propertyName)
    {
        if (this.containsAttribute(propertyName))
            this.attributes.remove(propertyName);
    }

    /**
     * Get the value associated with an attribute name.
     * 
     * @param propertyName name of the attribute
     * @return the value of the attribute
     */
    @Override
    public String getAttributeValue(String propertyName)
    {
        if (!containsAttribute(propertyName))
            return null;
        return this.attributes.get(propertyName);
    }

    /**
     * Determines if the object contains the specified attribute.
     * 
     * @param propertyName name of the attribute
     * @return true if the attribute exists, false otherwise
     */
    @Override
    public boolean containsAttribute(String propertyName)
    {
        return this.attributes.containsKey(propertyName);
    }

    /**
     * Set this object as th first element in the collection.
     */
    public void markAsFirstElement()
    {
        this.setFirstElement(true);
    }

    /**
     * Unset this object as the first element in the collection.
     */
    public void unmarkAsFirstElement()
    {
        this.setFirstElement(false);
    }

    /**
     * Determines if this object is set as the first object in the collection.
     * 
     * @return true if set as first object; false otherwise
     */
    public boolean isMarkedAsFirstElement()
    {
        return this.isFirstElement();
    }

  /**
     * Set this object as th last element in the collection.
     */
    public void markAsLastElement()
    {
        this.setLastElement(true);
    }

    /**
     * Unset this object as the last element in the collection.
     */
    public void unmarkAsLastElement()
    {
        this.setLastElement(false);
    }

    /**
     * Determines if this object is set as the last object in the collection.
     * 
     * @return true if set as last object; false otherwise
     */
    public boolean isMarkedAsLastElement()
    {
        return this.isLastElement();
    }

    /**
     * Get the object's timestamp.
     * 
     * @return the timestamp
     */
    public long getTimestamp()
    {
        return this.timestamp;
    }

    /**
     * Set the timestamp
     * @param inTimestamp the timestamp in milliseconds
     */
    public void setTimestamp(long inTimestamp)
    {
        this.timestamp = inTimestamp;
    }

    /**
     * Return a copy of this object.
     * 
     * @return
     */
    public abstract Object copy();

    /**
     * Change the object's first element status.
     * 
     * @param isFirstElement true if first; false otherwise
     */
    protected void setFirstElement(boolean isFirstElement)
    {
        this.isFirstElement = isFirstElement;
    }

    /**
     * Determine if the object is the first element.
     * 
     * @return true if th object is the first element; false otherwise
     */
    protected boolean isFirstElement()
    {
        return isFirstElement;
    }

    /**
     * Change the object's last element status.
     * 
     * @param isLastElement true if last; false otherwise
     */
    protected void setLastElement(boolean isLastElement)
    {
        this.isLastElement = isLastElement;
    }

    /**
     * Determine if the object is the last element.
     * 
     * @return true if th object is the last element; false otherwise
     */
    protected boolean isLastElement()
    {
        return isLastElement;
    }
}