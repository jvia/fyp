package org.bham.system.cast;

import java.io.Serializable;

/**
 *
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class CastData implements Serializable {
    
    private long timeStamp;
    private String eventType;
    private String generatorType;
    private String memoryType;

    public CastData(long timeStamp, String eventType, String generatorType, String memoryType)
    {
        this.timeStamp = timeStamp;
        this.eventType = eventType;
        this.generatorType = generatorType;
        this.memoryType = memoryType;
    }

    public String getEventType()
    {
        return eventType;
    }

    public String getGeneratorType()
    {
        return generatorType;
    }

    public String getMemoryType()
    {
        return memoryType;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    @Override
    public String toString()
    {
        return String.format("<cast eventType=\"%s\" generatorType=\"%s\" memoryType=\"%s\" />",
                             eventType, generatorType, memoryType);
    }
}
