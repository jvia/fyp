package org.bham.aucom.data.encoder;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.util.Constants;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.*;

/**
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class SourceScopeTypeEncoder extends Encoder {

    private static final long serialVersionUID = 1L;
    private static final String EncoderSeperator = ":";
    private static final String PATH = "/classes.txt";
    protected UUID id;
    protected ConcurrentHashMap<String, Integer> classes;
    private final Logger log = Logger.getLogger(getClass().getName());

    protected SourceScopeTypeEncoder() {
        id = UUID.randomUUID();
        classes = new ConcurrentHashMap<String, Integer>(new LinkedHashMap<String, Integer>());

        // Read in file and add to data structure
        Scanner s = null;
        try {
            s = new Scanner(getClass().getResourceAsStream(PATH));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] result = line.split("\\s+");
                classes.put(result[0].trim(), Integer.valueOf(result[1].trim()));
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public boolean isEncodingMissing(String inName) {
        return !this.classes.containsKey(inName);
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
        log.info(String.format("Encoding %s to STRING [%s] and ID [%d]", in, encodedString, encodedId));
        return encodedId;
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

    public static void createFeatures(String type, String source, String scope, List<DomainFeature> features) {
        features.add(new DomainFeature(Constants.EVENT_TYPE, type));
        features.add(new DomainFeature(Constants.SOURCE_TYPE, source));
        features.add(new DomainFeature(Constants.SCOPE_TYPE, scope));
    }

    public String createFeatureString(List<DomainFeature> features) {
        StringBuilder classAsString = new StringBuilder();
        for (DomainFeature f : features) {
            classAsString.append(f.getFeatureValue()).append(EncoderSeperator);
        }
        return classAsString.substring(0, classAsString.lastIndexOf(EncoderSeperator));
    }

    public List<DomainFeature> createFeaturesFromId(int id) {
        String str = decodeToString(id);
        List<DomainFeature> list = new LinkedList<DomainFeature>();
        String[] parts = str.split(EncoderSeperator);
        if (parts.length == 3) {
            createFeatures(parts[0], parts[1], parts[2], list);
        } else {
            log.warning("invalid mapping for " + id + ", with string representation: " + str);
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
            if (inId == this.classes.get(key)) {
                return key;
            }
        }
        return "";
    }

    private int convertEncoding(String d) {
        int encoding;
        if (isEncodingMissing(d)) {
            createEncodingFor(d);
            encoding = classes.get(d);
        } else {
            encoding = classes.get(d);
        }
        return encoding;
    }

    @Override
    public ConcurrentHashMap<String, Integer> getEncoding() {
        return this.classes;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void createEncodingFor(String str) {
        if (isEncodingMissing(str)) {
            int nextKey = getNextKey();
            getEncoding().put(str, nextKey);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int encode(List<DomainFeature> in) {
        return convertEncoding(createFeatureString(in));
    }
}
