package org.bham.aucom.data;

import junit.framework.Assert;
import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.util.Constants;

/**
 * Represents an Observation in a running system.
 * 
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class Observation extends AbstractData {

    private Element content = null;

    /**
     * Creates an Observation object.
     * 
     * @param content the observation content
     * @param timestamp the timestamp
     * @see nu.xom.Element
     */
    public Observation(Element content, long timestamp)
    {
        Assert.assertNotNull(content);
        Assert.assertTrue(timestamp != -1L);
        super.setTimestamp(timestamp);
        this.content = content;
    }

    /**
     * Creates a new observation from an object which is from type Observation 
     * or a subtype of it.
     * 
     * @param o
     */
    public Observation(Observation o)
    {
        if (o != null) {
            this.setTimestamp(o.getTimestamp());
            if (o.getContent() != null) {
                this.setContent(o.getContent());
            }
            this.attributes.putAll(o.attributes);
            this.setFirstElement(o.isFirstElement());
            this.setLastElement(o.isLastElement());
        }
    }

    /**
     * Creates an empty observation.
     */
    public Observation()
    {
        // TODO consider removing empty constructor
    }

    /**
     * Creates a random Observation object. Used for testing.
     * 
     * @return observation object
     */
    public static Observation createRandomObservation()
    {
        long timestamp = System.currentTimeMillis();
        Element content = new Element("content");
        content.addAttribute(new Attribute(Constants.EVENT_TYPE, "add"));
        content.addAttribute(new Attribute(Constants.SOURCE_TYPE, "monitor"));
        content.addAttribute(new Attribute(Constants.SCOPE_TYPE, "short"));
        content.appendChild(String.valueOf(Math.random() * 1000));
        return new Observation(content, timestamp);
    }

    /**
     * Get the content of the observation.
     * 
     * @return observation content
     */
    public Element getContent()
    {
        return this.content;
    }

    /**
     * Set the content xom document of this Observation
     * 
     * @param content set the content of the Observation
     */
    protected void setContent(Element content)
    {
        this.content = content;
    }

    /**
     * Creates a deep copy of this @Observation object. Be careful when using
     * this call, the whole content which is an xom document is copied too which
     * make the call expensive. TODO think about a shallow copy, too.
     * 
     * @return a copy of the Observation object
     */
    @Override
    public Object copy()
    {
        Observation newObservation = new Observation();
        newObservation.setTimestamp(getTimestamp());
        newObservation.setFirstElement(isMarkedAsFirstElement());
        newObservation.setLastElement(isMarkedAsLastElement());
        newObservation.setContent((Element) getContent().copy());
        return newObservation;
    }

    /**
     * Returns a String representation of the Observation.
     * @return
     */
    @Override
    public String toString()
    {
        return "[observation: " + getTimestamp() + "]";
    }
}
