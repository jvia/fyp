package org.bham.aucom.util;

public class RingBuffer<T> {
	T[] data;
	private int nextInsertPosition;
	private final int capacity;
	private boolean filled;

	@SuppressWarnings("unchecked")
	public RingBuffer(int inCapacity) {
		capacity = inCapacity;
		initializeBuffer();
	}
	public RingBuffer() {
		this(100000);
	}
	public int size(){
		if(isFilled()){
			return getCapacity();
		}
		return getNextInsertPosition();
	}

	/**
	 * @param inCapacity
	 */
        @SuppressWarnings("unchecked")
	private void initializeBuffer() {
		setFilled(false);
		data = (T[]) new Object[getCapacity()];
		setNextInsertPosition(0);
	}

	public void add(T element) {
		if (getNextInsertPosition() == getCapacity()) {
			setFilled(true);
			setNextInsertPosition(0);
		}
		data[getNextInsertPosition()] = element;
		setNextInsertPosition(getNextInsertPosition() + 1);
	}

	public void addAll(Object[] arryToAdd) {
		int numElementstoCopy = arryToAdd.length;
		int srcPos = 0;
		while (numElementstoCopy != 0) {
			if (getNextInsertPosition() == getCapacity()) {
				setFilled(true);
				setNextInsertPosition(0);
			}
			int from = getNextInsertPosition();
			if (from + numElementstoCopy <= getCapacity() - 1) {
				// passt noch ins restliche array rein
				System.arraycopy(arryToAdd, srcPos, data, from, numElementstoCopy);
				setNextInsertPosition(getNextInsertPosition() + numElementstoCopy);
				numElementstoCopy = 0;
				srcPos += numElementstoCopy - 1;
			} else {
				// muss aufgeteilt werden
				int temporalNumElementsToCopy = getCapacity() - from;
				System.arraycopy(arryToAdd, srcPos, data, from, temporalNumElementsToCopy);
				numElementstoCopy -= temporalNumElementsToCopy;
				setNextInsertPosition(from + temporalNumElementsToCopy);
				srcPos += temporalNumElementsToCopy;
			}
		}
	}

	/*
	 * returns the element at position index in the buffer If buffer is full the
	 * correct index of the value is dependent on the offset position
	 */
	public T get(int index) {
		if (index < 0 || index >= getCapacity()) {
			throw new IndexOutOfBoundsException(index + " exceeds boundaries  [0, " + (getCapacity() - 1) + "]");
		}
		if (isFilled()) {
			return data[(getNextInsertPosition() + index) % getCapacity()];
		}
		return data[index];
	}
	
	/**
	 * returns an array with elements of the buffer. If the buffer is full, the array has length {@linkplain getCapacity()} otherwise the array's length  is the number of inserted elements.  
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		T[] out = null;
		if (!isFilled()) {
		    out = (T[]) new Object[getNextInsertPosition()];
			System.arraycopy(data, 0, out, 0, getNextInsertPosition());
		} else {
		    out = (T[]) new Object[getCapacity()];
			System.arraycopy(data, getNextInsertPosition(), out, 0, getCapacity() - getNextInsertPosition());
			System.arraycopy(data, 0, out, getCapacity() - getNextInsertPosition(), getNextInsertPosition() );
		}
		return out;
	}

	protected void setFilled(boolean filled) {
		this.filled = filled;
	}

	public boolean isFilled() {
		return filled;
	}
	public boolean isEmpty(){
		return getNextInsertPosition() == 0;
	}

	public void clear() {
		initializeBuffer();
	}
	public int getCapacity() {
		return capacity;
	}
    private void setNextInsertPosition(int nextInsertPosition)
    {
        this.nextInsertPosition = nextInsertPosition;
    }
    protected int getNextInsertPosition()
    {
        return nextInsertPosition;
    }
}
