package org.bham.aucom.util;

/**
 * Represents a 2-tuple.
 *
 * @param <T> type of the first element
 * @param <U> type of the second element
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class Tuple<T, U> {

    private final T first;
    private U second;

    /**
     * Create the Tuple.
     *
     * @param inFirstElement  first element
     * @param inSecondElement second element
     */
    public Tuple(T inFirstElement, U inSecondElement) {
        first = inFirstElement;
        second = inSecondElement;
    }

    /**
     * Returns the first element of the tuple pair.
     *
     * @return first element
     */
    public T getFirst() {
        return first;
    }

    /**
     * Change the second element in the tuple pair.
     *
     * @param second the new element
     */
    public void setSecond(U second) {
        this.second = second;
    }

    /**
     * Returns the second element of the tuple pair.
     *
     * @return second element
     */
    public U getSecond() {
        return second;
    }

    /**
     * Return a String representation of the Tuple.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("(%s -> %s)", first, second);
    }
}
