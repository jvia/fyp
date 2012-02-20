package org.bham.aucom.data.timeseries;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bham.aucom.data.DataType;



public class DataTypeTimeSeries extends TimeSeries<DataType> {

        private static final long serialVersionUID = 0L;
	    
        /*
	 * 
	 * Constructors
	 */
	public DataTypeTimeSeries(UUID generatoId, UUID observationId) {
		this(generatoId, observationId, UUID.randomUUID(), new ArrayList<DataType>());
	}
	public DataTypeTimeSeries() {
		setType(TimeSeriesType.dtp);
	}
	
	public DataTypeTimeSeries(UUID generatorId, UUID observationId, UUID id, List<DataType> in) {
		super(generatorId, observationId, id,in);
		setType(TimeSeriesType.dtp);
	}
	
}
