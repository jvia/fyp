package org.bham.aucom.data;

import java.util.HashMap;

/**
 * Allows an object to have attributes associated with it.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public interface AttributableObject {

    /**
     * Return the collection of attributes that describe an object.
     *
     * @return collection of attributes
     */
    public HashMap<String, String> getAttributes();

    /**
     * Add an attribute about the object.
     *
     * @param propertyName  name of the attribute
     * @param propertyValue value of the attribute
     */
    public void addAttribute(String propertyName, String propertyValue);

    /**
     * Remove an attribute of an object.
     *
     * @param propertyName name of the attribute
     */
    public void deleteAttribute(String propertyName);

    /**
     * Get the value associated with an attribute name.
     *
     * @param propertyName name of the attribute
     * @return the value of the attribute
     */
    public String getAttributeValue(String propertyName);

    /**
     * Determines if the object contains the specified attribute.
     *
     * @param propertyName name of the attribute
     * @return true if the attribute exists, false otherwise
     */
    public boolean containsAttribute(String propertyName);
}
