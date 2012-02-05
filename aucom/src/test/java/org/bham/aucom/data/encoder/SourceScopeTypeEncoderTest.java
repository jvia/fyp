package org.bham.aucom.data.encoder;

import junit.framework.Assert;
import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.util.Constants;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;

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
//        Element e = createElementWithFeatures("type", "source", "scope");
//        List<DomainFeature> features = Encoder.getInstance().getFeatures(new Observation(e, System.currentTimeMillis()));
//        Assert.assertNotNull(features);
//        Assert.assertEquals(3, features.size());
//        Assert.assertEquals(Constants.EVENT_TYPE, features.get(0).getFeatureName());
//        Assert.assertEquals(Constants.SOURCE_TYPE, features.get(1).getFeatureName());
//        Assert.assertEquals(Constants.SCOPE_TYPE, features.get(2).getFeatureName());
//        Assert.assertEquals("type", features.get(0).getFeatureValue());
//        Assert.assertEquals("source", features.get(1).getFeatureValue());
//        Assert.assertEquals("scope", features.get(2).getFeatureValue());

        fail("To do");
    }

    @Test
    public void testCreateFeaturesFromString() {
//        String featureString = "a:b:c";
//        SourceScopeTypeEncoder encoder = (SourceScopeTypeEncoder) Encoder.getInstance();
//        List<DomainFeature> featureList = encoder.createFeaturesFromString(featureString);
//        Assert.assertEquals(3, featureList.size());
//        Assert.assertEquals("a", featureList.get(0).getFeatureValue());
//        Assert.assertEquals("b", featureList.get(1).getFeatureValue());
//        Assert.assertEquals("c", featureList.get(2).getFeatureValue());
        fail("To do");
    }


    @Test
    public void testCreateFeaturesFromId() throws IndexOutOfBoundsException {
//        String featureString = "a:b:c";
//        Integer featureId = Integer.valueOf(1);
//        SourceScopeTypeEncoder encoder = (SourceScopeTypeEncoder) Encoder.getInstance();
//        encoder.classes.clear();
//        List<DomainFeature> featureList = encoder.createFeaturesFromId(featureId.intValue());
//        Assert.assertNotNull(featureList);
//        Assert.assertEquals(0, featureList.size());
//
//        encoder.classes.put(featureString, featureId);
//        featureList = encoder.createFeaturesFromId(featureId.intValue());
//        Assert.assertEquals(3, featureList.size());
//        Assert.assertEquals("a", featureList.get(0).getFeatureValue());
//        Assert.assertEquals("b", featureList.get(1).getFeatureValue());
//        Assert.assertEquals("c", featureList.get(2).getFeatureValue());
        fail("To do");

    }

    @Test
    public void testDecode() {
        //SourceScopeTypeEncoder encoder = (SourceScopeTypeEncoder) Encoder.getInstance();
        //encoder.classes.clear();
        //List<DomainFeature> featureList = encoder.createFeaturesFromId(1);
        //Assert.assertNotNull(featureList);
        //Assert.assertEquals(0, featureList.size());
        fail("To do");
    }

    /**
     * Test of loadEncoding method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testLoadEncoding_InputStream() throws Exception {
        System.out.println("loadEncoding");
//        InputStream inStream = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.loadEncoding(inStream);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadEncoding method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testLoadEncoding_File() throws Exception {
        System.out.println("loadEncoding");
//        File inFile = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.loadEncoding(inFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEncodingMissing method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testIsEncodingMissing_String() {
        System.out.println("isEncodingMissing");
//        String inName = "";
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        boolean expResult = false;
//        boolean result = instance.isEncodingMissing(inName);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEncodingMissing method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testIsEncodingMissing_int() {
        System.out.println("isEncodingMissing");
//        int inId = 0;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        boolean expResult = false;
//        boolean result = instance.isEncodingMissing(inId);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenerator method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testGetGenerator() {
        System.out.println("getGenerator");
//        Element in = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        String expResult = "";
//        String result = instance.getGenerator(in);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getmemoryName method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testGetmemoryName() {
        System.out.println("getmemoryName");
//        Element in = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        String expResult = "";
//        String result = instance.getmemoryName(in);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testEncode_Observation() {
        System.out.println("encode");
        //Observation in = null;
        //SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
        //int expResult = 0;
        //int result = instance.encode(in);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encodeToString method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testEncodeToString() {
        System.out.println("encodeToString");
//        Observation in = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        String expResult = "";
//        String result = instance.encodeToString(in);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeatures method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testGetFeatures() {
        System.out.println("getFeatures");
        //Observation in = null;
        //SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
        //List expResult = null;
        //List result = instance.getFeatures(in);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFeatures method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testCreateFeatures() {
        System.out.println("createFeatures");
//        String type = "";
//        String source = "";
//        String scope = "";
//        List<DomainFeature> features = null;
//        SourceScopeTypeEncoder.createFeatures(type, source, scope, features);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFeatureString method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testCreateFeatureString() {
        System.out.println("createFeatureString");
//        List<DomainFeature> features = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        String expResult = "";
//        String result = instance.createFeatureString(features);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextKey method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testGetNextKey() {
        System.out.println("getNextKey");
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        int expResult = 0;
//        int result = instance.getNextKey();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEncodingWithName method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testCreateEncodingWithName() {
        System.out.println("createEncodingWithName");
//        String inName = "";
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.createEncodingWithName(inName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEncoding method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testGetEncoding() {
        System.out.println("getEncoding");
        //SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
        //ConcurrentHashMap expResult = null;
        //ConcurrentHashMap result = instance.getEncoding();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSingeltonInstance method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testSetSingeltonInstance() {
        System.out.println("setSingeltonInstance");
//        SourceScopeTypeEncoder singeltonInstance = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.setSingeltonInstance(singeltonInstance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingeltonInstance method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testGetSingeltonInstance() {
        System.out.println("getSingeltonInstance");
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        SourceScopeTypeEncoder expResult = null;
//        SourceScopeTypeEncoder result = instance.getSingeltonInstance();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
//        UUID id = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEncodingFor method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testCreateEncodingFor_Observation() {
        System.out.println("createEncodingFor");
//        Observation in = null;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.createEncodingFor(in);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEncodingFor method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testCreateEncodingFor_String() {
        System.out.println("createEncodingFor");
//        String str = "";
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.createEncodingFor(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCreateMissingEncoding method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testIsCreateMissingEncoding() {
        System.out.println("isCreateMissingEncoding");
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        boolean expResult = false;
//        boolean result = instance.isCreateMissingEncoding();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCreateMissingEncoding method, of class
     * SourceScopeTypeEncoder.
     */
    @Test
    public void testSetCreateMissingEncoding() {
        System.out.println("setCreateMissingEncoding");
//        boolean createMissingEncoding = false;
//        SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
//        instance.setCreateMissingEncoding(createMissingEncoding);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class SourceScopeTypeEncoder.
     */
    @Test
    public void testEncode_List() {
        System.out.println("encode");
        //List<DomainFeature> in = null;
        //SourceScopeTypeEncoder instance = new SourceScopeTypeEncoder();
        //int expResult = 0;
        //int result = instance.encode(in);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
