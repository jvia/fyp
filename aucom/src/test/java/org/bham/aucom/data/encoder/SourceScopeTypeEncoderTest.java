package org.bham.aucom.data.encoder;

import junit.framework.Assert;
import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.util.Constants;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SourceScopeTypeEncoderTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetEventType() {
        try {
            Element e = createElementWithoutFeatures();
            String str = SourceScopeTypeEncoder.getEventType(e);
            Assert.assertEquals("", str);
            String myAttributevalue = "helloWorldAttribute";
            e.addAttribute(new Attribute(Constants.EVENT_TYPE, myAttributevalue));
            str = SourceScopeTypeEncoder.getEventType(e);
            Assert.assertEquals(myAttributevalue, str);
        } catch (Exception exception) {
            Assert.fail("caught exception " + exception.getLocalizedMessage());
        }
    }

    /**
     * @return
     */
    private Element createElementWithoutFeatures() {
        Element e = new Element("test", Constants.URI);
        return e;
    }

    private Element createElementWithFeatures(String inFeatureValueOne, String inFeatureValueTwo, String inFeatureValueThree) {
        Element e = new Element("test", Constants.URI);
        e.addAttribute(new Attribute(Constants.EVENT_TYPE, inFeatureValueOne));
        e.addAttribute(new Attribute(Constants.SOURCE_TYPE, inFeatureValueTwo));
        e.addAttribute(new Attribute(Constants.SCOPE_TYPE, inFeatureValueThree));
        return e;
    }

    @Test
    public void testGetfeatures() {
        Element e = createElementWithFeatures("type", "source", "scope");
        List<DomainFeature> features = Encoder.getInstance().getFeatures(new Observation(e, System.currentTimeMillis()));
        Assert.assertNotNull(features);
        Assert.assertEquals(3, features.size());
        Assert.assertEquals(Constants.EVENT_TYPE, features.get(0).getFeatureName());
        Assert.assertEquals(Constants.SOURCE_TYPE, features.get(1).getFeatureName());
        Assert.assertEquals(Constants.SCOPE_TYPE, features.get(2).getFeatureName());
        Assert.assertEquals("type", features.get(0).getFeatureValue());
        Assert.assertEquals("source", features.get(1).getFeatureValue());
        Assert.assertEquals("scope", features.get(2).getFeatureValue());

    }

    @Test
    public void testCreateFeaturesFromString() {
        String featureString = "a:b:c";
        SourceScopeTypeEncoder encoder = (SourceScopeTypeEncoder) Encoder.getInstance();
        List<DomainFeature> featureList = encoder.createFeaturesFromString(featureString);
        Assert.assertEquals(3, featureList.size());
        Assert.assertEquals("a", featureList.get(0).getFeatureValue());
        Assert.assertEquals("b", featureList.get(1).getFeatureValue());
        Assert.assertEquals("c", featureList.get(2).getFeatureValue());
    }

    @Test
    public void testCreateFeaturesFromId() throws IndexOutOfBoundsException {
        String featureString = "a:b:c";
        Integer featureId = Integer.valueOf(1);
        SourceScopeTypeEncoder encoder = (SourceScopeTypeEncoder) Encoder.getInstance();
        encoder.classes.clear();
        List<DomainFeature> featureList = encoder.createFeaturesFromId(featureId.intValue());
        Assert.assertNotNull(featureList);
        Assert.assertEquals(0, featureList.size());

        encoder.classes.put(featureString, featureId);
        featureList = encoder.createFeaturesFromId(featureId.intValue());
        Assert.assertEquals(3, featureList.size());
        Assert.assertEquals("a", featureList.get(0).getFeatureValue());
        Assert.assertEquals("b", featureList.get(1).getFeatureValue());
        Assert.assertEquals("c", featureList.get(2).getFeatureValue());

    }

    @Test
    public void testDecode() {
        SourceScopeTypeEncoder encoder = (SourceScopeTypeEncoder) Encoder.getInstance();
        encoder.classes.clear();
        List<DomainFeature> featureList = encoder.createFeaturesFromId(1);
        Assert.assertNotNull(featureList);
        Assert.assertEquals(0, featureList.size());
    }
}
