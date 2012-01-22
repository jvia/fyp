package org.bham.aucom.data.encoder;

import static org.bham.aucom.util.Constants.EVENT_TYPE;
import static org.bham.aucom.util.Constants.SCOPE_TYPE;
import static org.bham.aucom.util.Constants.SOURCE_TYPE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;

import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.util.Constants;

/**
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class SourceScopeTypeEncoder extends Encoder {

	private static final long serialVersionUID = 1L;
	private static final String EncoderSeperator = ":";
	private static final String CLASSES_TXT_INTERN_PATH = File.separator+"data"+File.separator+"algorithm"+File.separator+"classes.txt";
	private static final String CLASSES_TXT_EXTERN_PATH = "data"+File.separator+"algorithm"+File.separator+"classes.txt";
	protected UUID id;
	protected ConcurrentHashMap<String, Integer> classes;
	protected boolean reportMissingEncoding = true;
	private boolean createMissingEncoding = true;
	protected static SourceScopeTypeEncoder singeltonInstance;

	protected SourceScopeTypeEncoder() {
		this.id = UUID.randomUUID();

		this.classes = new ConcurrentHashMap<String, Integer>(new LinkedHashMap<String, Integer>());
		load();
	}

	/**
     * 
     */
	private void load() {
		try {
			loadInternClassesFile();
		} catch (Exception e) {
			try {
				loadExternClassesFile();
			} catch (Exception ee) {
				Logger.getLogger(getClass().getCanonicalName()).severe("coudln't load any event type encoding." + "Reason: " + ee.getLocalizedMessage() + " Program will exit.");
				System.exit(1);
			}
		}
	}

	private void loadExternClassesFile() throws NumberFormatException, IOException {
		loadEncoding(new File(CLASSES_TXT_EXTERN_PATH));
	}

	private void loadInternClassesFile() throws NumberFormatException, IOException {
		InputStream inStream = this.getClass().getResourceAsStream(CLASSES_TXT_INTERN_PATH);
		loadEncoding(inStream);
	}

	// public static SourceScopeTypeEncoder getInstance() {
	// if (SourceScopeTypeEncoder.singeltonInstance == null)
	// SourceScopeTypeEncoder.singeltonInstance = new SourceScopeTypeEncoder();
	// return SourceScopeTypeEncoder.singeltonInstance;
	// }
	public void loadEncoding(InputStream inStream) throws NumberFormatException, IOException {
		this.classes.clear();
		BufferedReader is = new BufferedReader(new InputStreamReader(inStream));
		String tmp; //$NON-NLS-1$
		while ((tmp = is.readLine()) != null) {
			if (tmp.equals("")) //$NON-NLS-1$
				continue;
			tmp = tmp.trim();
			int splitIndex = tmp.indexOf(" "); //$NON-NLS-1$
			String clName = tmp.substring(0, splitIndex);
			Integer clVal = Integer.parseInt(tmp.substring(splitIndex + 1, tmp.length()));
			this.classes.put(clName, clVal);
		}
		System.out.println("ClassifyData: " + this.classes.size() + " classes loaded from \"" + CLASSES_TXT_INTERN_PATH + "\"");
	}

	public void loadEncoding(File inFile) throws NumberFormatException, IOException {
		this.classes.clear();
		BufferedReader is = new BufferedReader(new FileReader(inFile));
		String tmp; //$NON-NLS-1$
		while ((tmp = is.readLine()) != null) {
			if (tmp.equals("")) //$NON-NLS-1$
				continue;
			tmp = tmp.trim();
			int splitIndex = tmp.indexOf(" "); //$NON-NLS-1$
			String clName = tmp.substring(0, splitIndex);
			Integer clVal = Integer.parseInt(tmp.substring(splitIndex + 1, tmp.length()));
			this.classes.put(clName, clVal);
		}
		System.out.println("ClassifyData: " + this.classes.size() + " classes loaded from \"" + CLASSES_TXT_INTERN_PATH + "\"");
		is.close();
	}

	public boolean isEncodingMissing(String inName) {
		return !this.classes.containsKey(inName);
	}

	public boolean isEncodingMissing(int inId) {
		return !this.classes.containsValue(Integer.valueOf(inId));
	}

	public static String getEventType(Element in) {
		Attribute attr = in.getAttribute(EVENT_TYPE);
		String eventType = "";
		if (attr != null) {
			eventType = attr.getValue();
		}
		return eventType;
	}

	protected String getGenerator(Element in) {
		String generator = "";
		if (in.getAttribute(SOURCE_TYPE) != null) {
			generator = in.getAttribute(SOURCE_TYPE).getValue();
		} else if (in.getAttribute("generator") != null) {
			generator = in.getAttribute("generator").getValue();
		} else {
			Nodes nodes = in.query("./GENERATOR");
			if (nodes.size() > 0) {
				Node node = in.query("./GENERATOR").get(0);
				generator = node.getValue();
			} else if (generator.equals("") && in.getAttribute("source") != null) {
				generator = in.getAttribute("source").getValue();
			}
		}
		return generator;
	}

	protected String getmemoryName(Element in) {
		String memoryName = "";
		if (in.getAttribute(SCOPE_TYPE) != null) {
			memoryName = in.getAttribute(SCOPE_TYPE).getValue();
		} else if (in.getAttribute("memoryName") != null) {
			memoryName = in.getAttribute("memoryName").getValue();
		} else if (in.getAttribute("memoryname") != null) {
			memoryName = in.getAttribute("memoryname").getValue();
		}
		return memoryName;
	}

	@Override
	public int encode(Observation in) {
		List<DomainFeature> features = getFeatures(in);
		String encodedString = createFeatureString(features);
		int encodedId = convertEncoding(encodedString);
		Logger.getLogger(getClass().getCanonicalName()).log(Level.INFO, "encoding " + in + " to STRING [" + encodedString + "] and to  ID: " + encodedId);
		return encodedId;
	}

	public String encodeToString(Observation in) {
		int id = encode(in);
		return decodeToString(id);
	}

	@Override
	public List<DomainFeature> getFeatures(Observation in) {
		Element content = in.getContent();
		String type = getEventType(content);
		String source = getGenerator(content);
		String scope = getmemoryName(content);
		List<DomainFeature> features = new LinkedList<DomainFeature>();
		createFeatures(type, source, scope, features);
		return features;
	}

	/*
	 * @param type
	 * @param source
	 * @param scope
	 * @param features
	 */
	public static void createFeatures(String type, String source, String scope, List<DomainFeature> features) {
		features.add(new DomainFeature(Constants.EVENT_TYPE, type));
		features.add(new DomainFeature(Constants.SOURCE_TYPE, source));
		features.add(new DomainFeature(Constants.SCOPE_TYPE, scope));
	}

	public String createFeatureString(List<DomainFeature> features) {
		String classAsString = ""; //$NON-NLS-1$
		for (DomainFeature f : features) {
			classAsString += f.getFeatureValue() + EncoderSeperator;
		}
		return classAsString.substring(0, classAsString.lastIndexOf(EncoderSeperator));
	}

	public List<DomainFeature> createFeaturesFromString(String str) {
		// TODO broken, repair
		List<DomainFeature> list = new LinkedList<DomainFeature>();
		String[] parts = str.split(EncoderSeperator);
		createFeatures(parts[0], parts[1], parts[2], list);
		return list;
	}

	public List<DomainFeature> createFeaturesFromId(int id) {
		String str = decodeToString(id);
		List<DomainFeature> list = new LinkedList<DomainFeature>();
		String[] parts = str.split(EncoderSeperator);
		if(parts.length == 3){
			createFeatures(parts[0], parts[1], parts[2], list);
		}else{
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, "invalid mapping for " + id + ", with string representation: " + str);
		}
		return list;
	}

	protected int getNextKey() {
		if (this.classes.values().size() == 0) {
			return 1;
		}
		return new TreeSet<Integer>(this.classes.values()).last() + 1;
	}

	@Override
	public List<DomainFeature> decode(int id) {
		return createFeaturesFromId(id);
	}

	private String decodeToString(int inId) {
		for (String key : this.classes.keySet()) {
			if (inId == this.classes.get(key))
				return key;
		}
		return "";
	}

	private int convertEncoding(String d) {
		int encoding = -1;
		if (isEncodingMissing(d)) {
			if (createMissingEncoding) {
				createEncodingFor(d);
				encoding = this.classes.get(d);
			}
		} else {
			encoding = this.classes.get(d);
		}
		return encoding;
	}

	public void createEncodingWithName(String inName) {
		int nextKey = getNextKey();
		this.classes.put(inName, nextKey);
	}

	@Override
	public ConcurrentHashMap<String, Integer> getEncoding() {
		return this.classes;
	}

	void setSingeltonInstance(SourceScopeTypeEncoder singeltonInstance) {
		SourceScopeTypeEncoder.singeltonInstance = singeltonInstance;
	}

	SourceScopeTypeEncoder getSingeltonInstance() {
		return singeltonInstance;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void createEncodingFor(Observation in) {
		String str = encodeToString(in);
		createEncodingFor(str);
	}

	public void createEncodingFor(String str) {
		if (isEncodingMissing(str)) {
			int nextKey = getNextKey();
			getEncoding().put(str, nextKey);
		}
	}

	public boolean isCreateMissingEncoding() {
		return createMissingEncoding;
	}

	public void setCreateMissingEncoding(boolean createMissingEncoding) {
		this.createMissingEncoding = createMissingEncoding;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int encode(List<DomainFeature> in) {
		String str = createFeatureString(in);
		return convertEncoding(str);
	}
}
