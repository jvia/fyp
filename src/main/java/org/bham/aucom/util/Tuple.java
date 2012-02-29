package org.bham.aucom.util;

/**
 * Represents a 2-tuple.
 *
 * @param <T> type of the first element
 * @param <U> type of the second element
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class Tuple<T, U> {

    private T firstElement;
    private U secondElement;

    /**
     * Create the Tuple.
     *
     * @param inFirstElement  first element
     * @param inSecondElement second element
     */
    public Tuple(final T inFirstElement, final U inSecondElement) {
        this.firstElement = inFirstElement;
        this.secondElement = inSecondElement;
    }

    /**
     * Returns the first element of the tuple pair.
     *
     * @return first element
     */
    public T getFirstElement() {
        return firstElement;
    }

    /**
     * Change the second element in the tuple pair.
     *
     * @param secondElement the new element
     */
    public void setSecondElement(final U secondElement) {
        this.secondElement = secondElement;
    }

    /**
     * Returns the second element of the tuple pair.
     *
     * @return second element
     */
    public U getSecondElement() {
        return secondElement;
    }

    /**
     * Return a String representation of the Tuple.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return firstElement.toString() + "->" + secondElement.toString();
    }
}
