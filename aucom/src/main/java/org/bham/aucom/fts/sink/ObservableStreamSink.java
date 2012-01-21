package org.bham.aucom.fts.sink;

import java.io.OutputStream;
import java.util.logging.Logger;

import net.sf.xcf.fts.nodes.sink.BufferOutputEvent;
import nu.xom.Element;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.io.xml.out.converter.TimeSeriesToXmlDocumentConverter;
import org.bham.aucom.data.io.xml.out.converter.TimeSeriesToXmlDocumentConverterFactory;
import org.bham.aucom.util.Constants;

public class ObservableStreamSink<TIn extends AbstractData> extends AucomSinkAdapter<TIn> {
	private final OutputStream os;
	TimeSeriesToXmlDocumentConverterFactory factory = new TimeSeriesToXmlDocumentConverterFactory();
	public ObservableStreamSink(OutputStream os) {
		super("OutputStreamSink");
		this.os = os;
	}

	@Override
	protected void pushItem(TIn evt) throws Exception {
		if(evt == null){
			Logger.getLogger(this.getClass().getCanonicalName()).info("incoming event is null, I do nothing");
			return;
		}
		try {
		Element element= new Element("ts:element", Constants.URI);
		TimeSeriesToXmlDocumentConverter<TIn> converter =this.factory.getConverter(evt);
		if(converter != null){
			try {
				
				converter.appendElementContentAsXml(element, evt);
				String evtString = "\n" + element.toXML() + "\n";
				BufferOutputEvent boe = new BufferOutputEvent(evtString.getBytes());
				this.os.write(boe.buf, boe.offset, boe.length);
				this.os.flush();
				Logger.getLogger(this.getClass().getCanonicalName()).info("flushed event into outputstream");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("converter is null");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (evt.isMarkedAsLastElement()) {
			fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDLASTELEMENT));
			System.out.println("pushing last item " + evt);
		}
	}

}
