package org.bham.aucom.data;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DataTypeTest {
    @Before
    public void setup(){
    }
    @Test
    public void testToString() throws NullPointerException
    {
        DataType dtp;
        /*
         * default constructor
         */
        dtp = new DataType();
        dtp.toString();
        /*
         * copy constructor
         */
        dtp = new DataType(DataType.createRandomDataType());
        dtp.toString();
        /*
         * seperate fields constructor
         */
        dtp = new DataType(new ArrayList<DomainFeature>(), 1, Observation.createRandomObservation());
        dtp.toString();

        /*
         * seperate fields constructor with null feature list
         */
        dtp = new DataType(null, 1, Observation.createRandomObservation());
        dtp.toString();
    }

}
