package org.bham.aucom.data.timeseries;

import org.bham.aucom.data.LinkEnum;
import org.bham.aucom.data.Observation;

import java.util.List;
import java.util.UUID;

public class ObservationTimeSeries extends TimeSeries<Observation> {

    /*
      *
      * Constructors
      */

    /**
     *
     */
    private static final long serialVersionUID = -3349231759840972793L;

    public ObservationTimeSeries() {
        setType(TimeSeriesType.obs);
    }

    public ObservationTimeSeries(int num) {
        super(num);
        setType(TimeSeriesType.obs);
    }

    public ObservationTimeSeries(UUID generatorID, UUID generatedFromID, UUID id, List<Observation> in) {
        super(generatorID, generatedFromID, id, in);
        setType(TimeSeriesType.obs);
    }

    /*
      * Functions
      */
    public UUID getGeneratorId() {
        if (containsLink(LinkEnum.generatorId)) {
            return getLinks(LinkEnum.generatorId).get(0);
        }
        return null;
    }

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
