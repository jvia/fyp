package org.bham.aucom.data;

import org.bham.aucom.util.Tuple;

/**
 * This class represents a name-value pair of Strings that is used as a
 * domain feature.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class DomainFeature extends Tuple<String, String> {

    /**
     * Create the DomainFeature object.
     *
     * @param fratureName  name of the feature
     * @param featureValue value of the feature
     */
    public DomainFeature(String fratureName, String featureValue) {
        super(fratureName, featureValue);
    }

    /**
     * Get the feature name.
     *
     * @return feature name
     */
    public String getFeatureName() {
        return this.getFirstElement();
    }

    /**
     * Get the feature value.
     *
     * @return feature value
     */
    public String getFeatureValue() {
        return this.getSecondElement();
    }

    /**
     * Makes a deep copy of this domain feature object.
     */
    public Object copy() {
        DomainFeature df_copy = new DomainFeature(getFirstElement(), getSecondElement());
        return df_copy;
    }
}
