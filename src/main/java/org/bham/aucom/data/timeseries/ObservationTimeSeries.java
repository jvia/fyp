package org.bham.aucom.data.timeseries;

import org.bham.aucom.data.Observation;

public class ObservationTimeSeries extends TimeSeries<Observation> {

    private static final long serialVersionUID = -3349231759840972793L;

    public ObservationTimeSeries() {
        setType(TimeSeriesType.obs);
    }

// --Commented out by Inspection START (2/28/12 11:17 AM):
//    public ObservationTimeSeries(int num) {
//        super(num);
//        setType(TimeSeriesType.obs);
//    }
// --Commented out by Inspection STOP (2/28/12 11:17 AM)

// --Commented out by Inspection START (2/28/12 11:17 AM):
//    public ObservationTimeSeries(UUID generatorID, UUID generatedFromID, UUID id, List<Observation> in) {
//        super(generatorID, generatedFromID, id, in);
//        setType(TimeSeriesType.obs);
//    }
// --Commented out by Inspection STOP (2/28/12 11:17 AM)

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return false;
        }
        if (arg0.getClass().equals(this.getClass())) {
            ObservationTimeSeries s = (ObservationTimeSeries) arg0;
            return s.getId().equals(this.getId());
        }
        return false;
    }

    /**
     * No hashcode implemented. Do that before use in a hashmap.
     *
     * @return 42
     */
    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }
}
