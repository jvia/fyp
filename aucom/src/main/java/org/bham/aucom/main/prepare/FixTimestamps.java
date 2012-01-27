package org.bham.aucom.main.prepare;

import static org.bham.aucom.util.Constants.TIMESTAMP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nu.xom.XPathContext;
import org.bham.aucom.util.FileOperator;

public class FixTimestamps {
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
		new FixTimestamps().fix(file);

	}

	private Builder builder;

	public ArrayList<Element> extractXml(File inFile) {
		Document doc = null;
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

	private void fix(File file) {
		ArrayList<Element> list = extractXml(file);
		for (Element e : list) {
			fix2(e);
		}
		String sortedFileName = FileOperator.getPath(file) + File.separatorChar + FileOperator.getName(file);
		sortedFileName += "_tsFixed.dat"; //$NON-NLS-1$
		save(new File(sortedFileName), list);
	}

	public void save(File file, ArrayList<Element> list) {
		try {
			FileWriter out;
			out = new FileWriter(file);
			Logger.getLogger(this.getClass().getName()).info("flushing started");
			Document doc = new Document(new Element("events"));
			for (Element d : list) {
				try {
					doc.getRootElement().appendChild(d.copy());
				} catch (Exception e) {
					System.out.println(d);
					System.exit(0);
				}
			}
			out.write(doc.toXML());
			out.close();
			System.out.println(doc.getRootElement().getChildCount() + " events flushed to " + file.getName());
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getCanonicalName()).severe(e.getLocalizedMessage());
		} catch (NullPointerException e) {
			Logger.getLogger(this.getClass().getCanonicalName()).severe(e.getLocalizedMessage());
		}
	}

	private void fix(Element e) {
		long timestamp = getTimestamp(e);
		if (timestamp == -1) {
			timestamp = getAlternativeTimestamp(e);
		}
		if (timestamp != -1) {
			e.removeAttribute(e.getAttribute("timestamp"));
			e.addAttribute(new Attribute("timestamp", String.valueOf(timestamp)));
		} else {
			System.out.println("could not fixed timestamp for node: " + e.getLocalName());
		}
	}

	private void fix2(Element e) {
		long timestamp = getTimestamp2(e);
		e.removeAttribute(e.getAttribute("timestamp"));
		e.addAttribute(new Attribute("timestamp", String.valueOf(timestamp)));
	}

	private long getTimestamp2(Element in) throws NumberFormatException {
		String out = "";
		XPathContext context = new XPathContext("xcf", "http://xcf.sf.net");
		Nodes nodes = in.query("//ts[@key='xcf:pub']", context);
		if (nodes.size() > 0) {
			out = ((Element) nodes.get(0)).getAttributeValue("ms");
		}
		if (out.equals("")) {
			long alternativeTimestamp = getAlternativeTimestamp(in);
			if (alternativeTimestamp != -1) {
				return alternativeTimestamp;
			} else {
				System.out.println("coudn't fix timestamp");
				System.exit(0);
			}
		}
		return Long.parseLong(out);
	}

	private long getTimestamp(Element in) throws NumberFormatException {
		String timestamp = "";
		long out = 0L;
		if (in.getAttribute("timestamp") != null) {
			timestamp = in.getAttribute("timestamp").getValue().toString();
		} else if (in.getAttribute(TIMESTAMP) != null) {
			timestamp = in.getAttribute(TIMESTAMP).getValue().toString();
		}
		try {
			out = Long.parseLong(timestamp);
		} catch (NumberFormatException e) {
			out = -1;
		}
		return out;
	}

	private long getAlternativeTimestamp(Element in) throws NumberFormatException {
		String timestamp = "";
		long out = 0L;
		String name = in.getLocalName();
		if (name.equals("speech_hyp")) {
			XPathContext context = new XPathContext("xcf", "");
			Nodes nodes = in.query("//TIMESTAMP", context);
			if (nodes.size() > 0) {
				timestamp = nodes.get(0).getValue();
			}
		} else if (name.equals("PERSON") || name.equals("PERCEPTS")) {
			XPathContext context = new XPathContext("xcf", "");
			Nodes nodes = in.query("//TIMESTAMP//UPDATED", context);
			if (nodes.size() > 0) {
				timestamp = ((Element) nodes.get(0)).getAttributeValue("value");
			}
		}
		try {
			out = Long.parseLong(timestamp);
		} catch (NumberFormatException e) {
			out = -1;
		}
		return out;
	}
}
