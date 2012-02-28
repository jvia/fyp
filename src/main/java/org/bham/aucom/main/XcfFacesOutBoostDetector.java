package org.bham.aucom.main;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.util.FileOperator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class XcfFacesOutBoostDetector {
    ArrayList<Document> recordedDocumentsList;

    public XcfFacesOutBoostDetector(File f) {
        recordedDocumentsList = new ArrayList<Document>();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        File file = new File(args[0]);
        String name = FileOperator.getName(file);
        File file2 = new File(name + "_cc.dat");
        Builder builder = new Builder();

        try {
            Document doc = builder.build(file);
            Elements elements = doc.getRootElement().getChildElements();
            ArrayList<Element> org = new ArrayList<Element>();

            for (int i = 0; i < elements.size(); i++) {
                org.add(elements.get(i));
            }

            long firstTimestamp = Long.valueOf(org.get(0).getAttribute("timestamp").getValue());
            long timestamp = Long.valueOf(org.get(org.size() - 1).getAttribute("timestamp").getValue());
            ArrayList<Element> xcfFacesOut = generateXcfFacesOuts(timestamp, firstTimestamp + 120);
            ArrayList<Element> boostDetector = generateBoostDetectoor(timestamp, firstTimestamp + 240);
            System.out.println("extracting " + org.size() + " elements from " + file.getName() + "file");
            ArrayList<Element> merged = merge(org, xcfFacesOut, boostDetector);

            save(merged, file2);
        } catch (ValidityException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // private static ArrayList<Element> merge2(ArrayList<Element> merged) {
    //  ArrayList<Element> out = new ArrayList<Element>();
    //  for(Element e: merged){
    //   if(e.getAttribute(""))
    //  }
    //  return null;
    //
    // }
    private static ArrayList<Element> merge(ArrayList<Element> org,
                                            ArrayList<Element> xcfFacesOut, ArrayList<Element> boostDetector) {
        ArrayList<Element> out = new ArrayList<Element>();
        Element original = null;
        Element xcf = null;
        Element boost = null;
        Iterator<Element> org_it = org.iterator();
        Iterator<Element> xcf_it = xcfFacesOut.iterator();
        Iterator<Element> boost_it = boostDetector.iterator();
        int size = org.size() + xcfFacesOut.size() + boostDetector.size();
        for (int i = 0; i < size; i++) {
            Element tmp = null;
            if (original == null && org_it.hasNext()) {
                original = org_it.next();
            }
            if (xcf == null && xcf_it.hasNext()) {
                xcf = xcf_it.next();
            }
            if (boost == null && boost_it.hasNext()) {
                boost = boost_it.next();
            }
            if (original != null) {
                tmp = original;
                if (xcf != null) {
                    if (Long.valueOf(xcf.getAttribute("timestamp").getValue()) < Long.valueOf(tmp.getAttribute("timestamp").getValue())) {
                        tmp = xcf;
                    }
                }
                if (boost != null) {
                    if (Long.valueOf(boost.getAttribute("timestamp").getValue()) < Long.valueOf(tmp.getAttribute("timestamp").getValue())) {
                        tmp = boost;
                    }
                }
            } else if (xcf != null) {
                tmp = xcf;
                if (boost != null) {
                    if (Long.valueOf(boost.getAttribute("timestamp").getValue()) < Long.valueOf(tmp.getAttribute("timestamp").getValue())) {
                        tmp = boost;
                    }
                }
            } else {
                if (boost != null) {
                    tmp = boost;
                }
            }
            if (tmp == null) {
                System.out.println("error tmp can't be null in iteration " + i);
                System.exit(1);
            }
            out.add((Element) tmp.copy());
            if (tmp == original) {
                original = null;
            } else if (tmp == xcf) {
                xcf = null;
            } else if (tmp == boost) {
                boost = null;
            } else {
                System.out.print("ERROR tmp has to be equal to either original, xcf or boost");
                System.exit(1);
            }
        }
        return out;
    }

    public static ArrayList<Element> generateBoostDetectoor(long duration, long offset) {
        ArrayList<Element> boostDetector = new ArrayList<Element>();
        long timestamp = offset;
        Element tmp = null;
        int counter = 0;
        while (timestamp < duration) {
            timestamp += (long) (400 + (50 - Math.random() * 50));

            System.out.println("tmpTimestamp" + counter + " " + timestamp);
            tmp = new Element("event");
            tmp.addAttribute(new Attribute("generatorType", "BoostDetector"));

            tmp.addAttribute(new Attribute("timestamp", String.valueOf(timestamp)));
            tmp.addAttribute(new Attribute("eventType", "INSERT"));
            tmp.addAttribute(new Attribute("memoryName", "publisher"));
            tmp.addAttribute(new Attribute("dataSetId", "XCF"));
            tmp.addAttribute(new Attribute("dataSetIndex", "0"));
            counter++;
            boostDetector.add(tmp);
        }
        return boostDetector;
    }

    public static ArrayList<Element> generateXcfFacesOuts(long duration, long offset) {
        ArrayList<Element> xcfFacesout = new ArrayList<Element>();
        long timestamp = offset;
        Element tmp = null;
        int counter = 0;
        while (timestamp < duration) {
            timestamp += (long) (460 + (50 - Math.random() * 50));

            System.out.println("tmpTimestamp" + counter + " " + timestamp);
            tmp = new Element("event");

            tmp.addAttribute(new Attribute("generatorType", "xcfFacesOut"));

            tmp.addAttribute(new Attribute("timestamp", String.valueOf(timestamp)));
            tmp.addAttribute(new Attribute("eventType", "INSERT"));
            tmp.addAttribute(new Attribute("memoryName", "publisher"));
            tmp.addAttribute(new Attribute("dataSetId", "XCF"));
            tmp.addAttribute(new Attribute("dataSetIndex", "0"));
            counter++;
            xcfFacesout.add(tmp);
        }
        System.out.println();
        return xcfFacesout;
    }

    public static void save(ArrayList<Element> elements, File file) {
        try {
            FileWriter out;
            out = new FileWriter(file);
            Document doc = new Document(new Element("events"));
            for (Element el : elements) {
                try {
                    doc.getRootElement().appendChild(el.copy());
                } catch (Exception e) {
                    System.out.println(el);
                    System.exit(0);
                }
            }
            out.write(doc.toXML());
            out.close();
            System.out.println(doc.getRootElement().getChildCount()
                               + " events flushed to " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
