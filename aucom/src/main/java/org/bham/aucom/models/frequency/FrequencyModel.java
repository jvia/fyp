package org.bham.aucom.models.frequency;

import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;

import java.util.LinkedHashMap;

public class FrequencyModel {

    private LinkedHashMap<Integer, Integer> data;
    private int numValues;

    public FrequencyModel()
    {
        data = new LinkedHashMap<Integer, Integer>();
        numValues = 0;
    }

    public void put(Integer classId)
    {
        if (!getData().containsKey(classId))
            getData().put(classId, new Integer(0));
        getData().put(classId, getData().get(classId) + 1);
        setNumValues(getNumValues() + 1);
    }

    public double getProbability(Integer classId)
    {
        if (!getData().containsKey(classId))
            return LOWESTPROBABILITY;
        return getData().get(classId).doubleValue() / getNumValues();
    }

    public void setData(LinkedHashMap<Integer, Integer> data)
    {
        this.data = data;
    }

    public LinkedHashMap<Integer, Integer> getData()
    {
        return this.data;
    }

    public void setNumValues(int numValues)
    {
        this.numValues = numValues;
    }

    public int getNumValues()
    {
        return this.numValues;
    }
}
