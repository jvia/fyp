package org.bham.aucom.main;

import static org.bham.aucom.util.Constants.TIMESTAMP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nu.xom.XPathContext;

import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.util.Tupel;
import org.jaxen.JaxenException;
import org.jaxen.xom.XOMXPath;


public class Report {
	public static final String GENERATOR_TYPE = "generatorType";
	private LinkedHashMap<String, Element> corruptedTimestampsElements;
	private LinkedHashMap<String, Integer> zeroTimestampsElements;
	private LinkedHashMap<String, Tupel<Element, Element>> negativeTimestampsElements;
	private Builder builder;
	private int numCorruptedTimestamps;
	private int numZeroTimestamps;
	private int numIsShortTermIdenticaTimestamps;
	private int numNegativeTimestamps;
	private int numTotal;
	private int numMissingMetaData;
	private LinkedHashMap<String, Integer> missingTimestampsElements;

	private Document createReport(File file) throws FileNotFoundException, ValidityException, DataAlreadyExistsException, ParsingException, IOException {
		ArrayList<Element> list = extractXml(file);
		this.numTotal = list.size();
		LinkedHashMap<String, Integer> components = analyseComponents(list);
		printComponents(components, list);
		analyseMissingMetaData(list);
		analyseCorruptedTimestamps(list);
//		analyseUnorderedTimestamps(list);
//		analyseIdenticalTimestamps(list);
		reportTimestamps();
		return null;
	}

	private void analyseMissingMetaData(ArrayList<Element> list) {
		for (Element element : list) {
			XPathContext context = new XPathContext("xcf", "http://xcf.sf.net");
			Nodes nodes = element.query("./xcf:metadata", context);
			if (nodes.size() == 0){
				this.numMissingMetaData++;
				this.missingTimestampsElements.put(element.getLocalName(), 1);
			}else{
//				System.out.println("--> " +nodes.size());
//				System.out.println(nodes.get(0).toXML());
//				System.out.println("<--");
			}
		}


	}

	public void printComponents(LinkedHashMap<String, Integer> components, ArrayList<Element> elements) {
		DecimalFormat format = new DecimalFormat("0.000");
		double duration = -1.0d;
		for (int i = 1; i <= elements.size(); i++) {
			try {
				duration = getTimestamp(elements.get(elements.size() - i)) - getTimestamp(elements.get(0));
			} catch (Exception exception) {
				System.out.println("timestamp broken or missing");
			}
			if (duration != -1)
				break;
		}
		duration /= 1000.0d;
		System.out.println("++++++++++++++++++ COMPONENTS  ++++++++++++++++++");
		System.out.println("Number Components " + components.keySet().size());
		System.out.println("Duration " + format.format(duration));
		System.out.println("Overall frequency " + format.format(elements.size() / duration) + "Hz");
		double total = 0;
		for (String s : components.keySet()) {
			total += components.get(s);
		}
		for (String s : components.keySet()) {
			System.out.println(s + " " + format.format(100.0 * components.get(s).doubleValue() / total) + " frequency " + format.format(components.get(s).doubleValue() / duration) + "Hz");
		}
		System.out.println("++++++++++++++++++ COMPONENTS ++++++++++++++++++");
	}

	public LinkedHashMap<String, Integer> analyseComponents(ArrayList<Element> list) {
		LinkedHashMap<String, Integer> out = new LinkedHashMap<String, Integer>();
		for (Element e : list) {
			String gen = getGenerator(e);
			if (!out.containsKey(gen))
				out.put(gen, 0);
			out.put(gen, out.get(gen) + 1);
		}
		return out;
	}

	public Report() {
		this.corruptedTimestampsElements = new LinkedHashMap<String, Element>();
		this.zeroTimestampsElements = new LinkedHashMap<String, Integer>();
		this.negativeTimestampsElements = new LinkedHashMap<String, Tupel<Element, Element>>();
		this.missingTimestampsElements = new LinkedHashMap<String, Integer>();
		this.numCorruptedTimestamps = 0;
		this.numZeroTimestamps = 0;
		this.numIsShortTermIdenticaTimestamps = 0;
		this.numNegativeTimestamps = 0;
		this.numTotal = 0;
		this.numMissingMetaData = 0;
		// this.classifier = new
	}

	private String getGenerator(Element in) {
		String generator = "";
		if (in.getAttribute(GENERATOR_TYPE) != null) {
			generator = in.getAttribute(GENERATOR_TYPE).getValue();
			// System.out.println("generator1 " +generator);
		} else if (in.getAttribute("generator") != null) {
			generator = in.getAttribute("generator").getValue();
			// System.out.println("generator2 " +generator);
		} else {
			try {
				XOMXPath request = new XOMXPath("//GENERATOR");
                                @SuppressWarnings("unchecked")
				List<Element> nodes = request.selectNodes(new Document(in));
				if (nodes.size() > 0) {
					Element element = nodes.iterator().next();
					// System.out.println("bockmist" + element.toXML());
					generator = element.getValue();
				}
			} catch (JaxenException e) {
				e.printStackTrace();
			}
			// System.out.println("generator3 " +generator);
			if (generator.equals("") && in.getAttribute("source") != null) {
				generator = in.getAttribute("source").getValue();
			}
			// System.out.println("generator4 " +generator);
			// System.out.println("<-------------------");
			// System.out.println("");
		}
		return generator;
	}

	private long getTimestamp(Element in) throws NumberFormatException {
		String timestamp = "";
		long out;
		if (in.getAttribute("timestamp") != null) {
			timestamp = in.getAttribute("timestamp").getValue();
		} else if (in.getAttribute(TIMESTAMP) != null) {
			timestamp = in.getAttribute(TIMESTAMP).getValue();
		}
		out = Long.parseLong(timestamp);
		return out;
	}

	public void reportTimestamps() {
		System.out.println("---------------- TIMESTAMPS (" + numTotal + ")----------------");
		System.out.println("++++++++++++++++++ Missing METADATA (" + this.numMissingMetaData + ") " + (double) this.numMissingMetaData/ (double) this.numTotal  + "  ++++++++++++++++++");
		for (String key : this.missingTimestampsElements.keySet()) {
			System.out.println("# " + key);
		}
		System.out.println("++++++++++++++++++ Missing METADATA   ++++++++++++++++++");
		System.out.println("++++++++++++++++++ CORRUPT (" + numCorruptedTimestamps + ") %" + (double) numCorruptedTimestamps / (double) numTotal + " ++++++++++++++++++");
		for (String key : corruptedTimestampsElements.keySet()) {
			System.out.println("# " + key);
			System.out.println(this.corruptedTimestampsElements.get(key).toXML());
		}
		System.out.println("++++++++++++++++++ CORRUPT ++++++++++++++++++");
		System.out.println("****************** ZERO (" + numZeroTimestamps + ") %" + (double) numZeroTimestamps / numTotal + "ShortTerm (" + this.numIsShortTermIdenticaTimestamps + ") %"
				+ (double) this.numIsShortTermIdenticaTimestamps / numTotal + " ******************");
		for (String key : zeroTimestampsElements.keySet()) {
			System.out.println(key + ": " + this.zeroTimestampsElements.get(key) + " : " + (double) this.zeroTimestampsElements.get(key) / numTotal);
		}

		System.out.println("****************** ZERO ******************");
		System.out.println("****************** NEGATIVE (" + numNegativeTimestamps + ") %" + (double) numNegativeTimestamps / numTotal + " ******************");
		for (String key : negativeTimestampsElements.keySet()) {
			System.out.println("from : " + this.negativeTimestampsElements.get(key).getFirstElement().getLocalName() + " " + getTimestamp(this.negativeTimestampsElements.get(key).getFirstElement())
					+ "ms " + " to: " + this.negativeTimestampsElements.get(key).getSecondElement().getLocalName() + " " + getTimestamp(this.negativeTimestampsElements.get(key).getFirstElement())
					+ "ms ");
		}

		System.out.println("****************** NEGATIVE ******************");
		System.out.println("---------------- TIMESTAMPS ----------------");
	}

	private void analyseIdenticalTimestamps(ArrayList<Element> list) {
		ArrayList<Element> proofed = new ArrayList<Element>();
		while (!list.isEmpty()) {
			Element e = list.get(0);
			proofed.clear();
			try {
				long timestamp = getTimestamp(e);
				if (timestamp == -1) {
					continue;
				}
				for (int j = 1; j < list.size(); j++) {
					try {
						Element ee = list.get(j);
						long timestamp2 = getTimestamp(ee);
						if (timestamp2 == -1) {
							continue;
						}
						if (e.equals(ee)) {
							continue;
						}
						long diff = timestamp - timestamp2;
						if (diff == 0) {
							this.numZeroTimestamps++;
							String type = e.getAttributeValue("source") + ":" + e.getAttributeValue("memoryName") + ":" + e.getAttributeValue("eventType") + ":" + e.getLocalName() + " -> "
									+ e.getAttributeValue("source") + ":" + e.getAttributeValue("memoryName") + ":" + e.getAttributeValue("eventType") + ":" + ee.getLocalName();
							if (!this.zeroTimestampsElements.containsKey(type))
								this.zeroTimestampsElements.put(type, 0);
							this.zeroTimestampsElements.put(type, this.zeroTimestampsElements.get(type) + 1);
							proofed.add(ee);
						}
					} catch (Exception e2) {
						for (Element remove : proofed) {
							list.remove(remove);
						}
					}
				}
				for (Element remove : proofed) {
					list.remove(remove);
				}
			} catch (NumberFormatException e2) {
				for (Element remove : proofed) {
					list.remove(remove);
				}
			}
			list.remove(e);
		}
	}

	private void analyseCorruptedTimestamps(ArrayList<Element> list) {
		for (Element e : list) {
			try {
                getTimestamp(e);
			} catch (NumberFormatException e2) {
				numCorruptedTimestamps++;
				corruptedTimestampsElements.put(e.getLocalName(), e);
			}
		}
	}

	private void analyseUnorderedTimestamps(ArrayList<Element> list) {
		long lastTimestamp = -1;
		Element lastTimestampElement = null;
		for (Element e : list) {
			try {
				long timestamp = getTimestamp(e);
				if (lastTimestamp == -1) {
					lastTimestamp = timestamp;
					lastTimestampElement = e;
					continue;
				}
				long diff = timestamp - lastTimestamp;
				if (diff < 0) {
					this.numNegativeTimestamps++;
					this.negativeTimestampsElements.put(e.getLocalName(), new Tupel<Element, Element>(lastTimestampElement, e));

				}
				lastTimestampElement = e;
				lastTimestamp = timestamp;

			} catch (NumberFormatException e2) {
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("usage: report file.obs");
			return;
		}
		File file = new File(args[0]);
		if (!file.exists() || !file.isFile()) {
			System.out.println("file " + args[0] + " not found");
		}
		try {
            new Report().createReport(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ValidityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DataAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParsingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
}
