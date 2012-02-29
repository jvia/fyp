package org.bham.aucom.data;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.util.Constants;

/**
 * Represents an Observation in a running system.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class Observation extends AbstractData {

    private Element content = null;

    /**
     * Creates an Observation object.
     *
     * @param content   the observation content
     * @param timestamp the timestamp
     * @see nu.xom.Element
     */
    public Observation(final Element content, final long timestamp) {
        super.setTimestamp(timestamp);
        this.content = content;
    }

    /**
     * Creates a new observation from an object which is from type Observation
     * or a subtype of it.
     *
     * @param o the observation
     */
    public Observation(final Observation o) {
        if (o != null) {
            setTimestamp(o.getTimestamp());
            if (o.getContent() != null) {
                setContent(o.getContent());
            }
            attributes.putAll(o.attributes);
            setFirstElement(o.isFirstElement());
            setLastElement(o.isLastElement());
        }
    }

    public Observation() {}

    /**
     * Creates a random Observation object. Used for testing.
     *
     * @return observation object
     */
    public static Observation createRandomObservation() {
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
    public Element getContent() {
        return content;
    }

    /**
     * Set the content xom document of this Observation
     *
     * @param content set the content of the Observation
     */
    protected void setContent(Element content) {
        this.content = content;
    }

    /**
     * Creates a deep copy of this @Observation object. Be careful when using
     * this call, the whole content which, is an XOM document is copied too,
     * which makes the call expensive.
     * <p/>
     * TODO think about a shallow copy, too.
     *
     * @return a copy of the Observation object
     */
    @Override
    public Object copy() {
        Observation newObservation = new Observation((Element) getContent().copy(), getTimestamp());
        newObservation.setFirstElement(isFirstElement());
        newObservation.setLastElement(isLastElement());
        return newObservation;
    }

    /**
     * Returns a String representation of the Observation.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "[observation: " + getTimestamp() + "]";
    }
}
