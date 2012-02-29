package org.bham.aucom.main.prepare;

import nu.xom.*;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.FileOperator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ConvertFromDatToObservationTimeseries {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            new ConvertFromDatToObservationTimeseries().convertDatToObservation(args[0]);

        }
    }

    /**
     * @param args
     */
    public void convertDatToObservation(String str) {
        Builder b = new Builder();
        try {
            Element rootElement = new Element("ts:timeseries", Constants.URI);
            rootElement.addAttribute(new Attribute("id", UUID.randomUUID().toString()));
            rootElement.addAttribute(new Attribute("type", "obs"));
            Document out = new Document(rootElement);
            File inputfile = new File(str);
            Document doc = b.build(inputfile);
            Element elements = new Element("ts:elements", Constants.URI);
            Nodes nodes = doc.getRootElement().query("./*");
            rootElement.appendChild(elements);
            for (int i = 0; i < nodes.size(); i++) {
                Element element = new Element("ts:element", Constants.URI);
                Element observation = new Element("ts:observation", Constants.URI);
                observation.addAttribute(new Attribute("timestamp", getTimestampStringFromNode(nodes.get(i))));
                observation.appendChild(nodes.get(i).copy());
                element.appendChild(observation);
                elements.appendChild(element);
            }
            String filename = FileOperator.getPath(inputfile) + File.separatorChar + FileOperator.getName(inputfile) + ".obs";
            System.out.println("writing to " + filename);
            FileOutputStream fileStream = new FileOutputStream(filename);
            Serializer ser = new Serializer(fileStream);
            ser.write(out);
            fileStream.close();
        } catch (ValidityException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (ParsingException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
    }

    private static String getTimestampStringFromNode(Node node) {
        return ((Element) node).getAttributeValue("timestamp");
    }

}
