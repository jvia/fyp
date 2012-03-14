package org.bham.aucom.main.prepare;

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
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.TIMESTAMP;

public class Order {
    private Builder builder;

    public void order(File f) {
        ArrayList<Element> unsortedList = extractXml(f);
        SortedMap<Long, Element> sortedMap = sort(unsortedList);
        String sortedFileName = FileOperator.getName(f);
        sortedFileName += "_sorted.dat";
        save(new File(sortedFileName), sortedMap);
    }

    private long getTimestamp(Element in) {
        String timestamp = "";
        long out = -1;
        if (in.getAttribute("timestamp") != null) {
            timestamp = in.getAttribute("timestamp")
                          .getValue();
        } else if (in.getAttribute(TIMESTAMP) != null) {
            timestamp = in.getAttribute(TIMESTAMP).getValue();
        }
        try {
            out = Long.parseLong(timestamp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return out;
    }

    public SortedMap<Long, Element> sort(ArrayList<Element> list) {
        SortedMap<Long, Element> sortedMap = new TreeMap<Long, Element>();
        for (Element element : list) {
            long currTimestamp = getTimestamp(element);
            if (currTimestamp == -1) {
                System.out.println("removing " + element.getLocalName() + " due to corrupted timestamp: '" + currTimestamp + "'");
                continue;
            }
            sortedMap.put(currTimestamp, element);

        }
        return sortedMap;
    }

    public ArrayList<Element> extractXml(File inFile) {
        Document doc;
        ArrayList<Element> list = null;
        if (this.builder == null) {
            this.builder = new Builder();
        }
        try {
            doc = this.builder.build(inFile);
            Elements elements = doc.getRootElement().getChildElements();
            list = new ArrayList<Element>();
            for (int i = 0; i < elements.size(); i++) {
                list.add((Element) elements.get(i).copy());
            }

        } catch (ValidityException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("wrong number of arguments");
            return;
        }
        File file = new File(args[0]);
        if (!file.exists() || !file.isFile()) {
            System.out.println("file " + args[0] + " not found");
        }
        new Order().order(file);
    }

    public void save(File file, SortedMap<Long, Element> list) {
        try {
            FileWriter out;
            out = new FileWriter(file);
            Logger.getLogger(this.getClass().getName())
                  .info("flushing started");
            Document doc = new Document(new Element("events"));
            for (Element d : list.values()) {
                try {
                    doc.getRootElement().appendChild(d.copy());
                } catch (Exception e) {
                    System.out.println(d);
                    System.exit(0);
                }
            }
            out.write(doc.toXML());
            out.close();
            System.out.println(doc.getRootElement().getChildCount()
                               + " events flushed to " + file.getName());
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getCanonicalName()).severe(
                    e.getLocalizedMessage());
        } catch (NullPointerException e) {
            Logger.getLogger(this.getClass().getCanonicalName()).severe(
                    e.getLocalizedMessage());
        }
    }
}
