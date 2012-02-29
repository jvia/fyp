package org.bham.system.cast;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.Observation;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.AucomSourceAdapter;
import org.bham.aucom.fts.source.SourceStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
class CastObservationSource extends AucomSourceAdapter<Observation> {

    private transient ConnectionManager cast;
    private transient Logger log = Logger.getLogger(getClass().getName());
    private transient final ArrayList<Observation> history;

    private final LinkedBlockingQueue<String[]> queue;
    private long count;

    public CastObservationSource() {
        super("CastObservationSource");
        queue = new LinkedBlockingQueue<String[]>();
        count = 0;
        history = new ArrayList<Observation>();
    }

    @Override
    protected void iDisconnect() throws ActionFailedException {
        cast.shutdown();
        setState(SourceStatus.DISCONNECTED);
        log.info("Disconnected");
    }

    @Override
    protected void iConnect() throws ActionFailedException {
        if (getStatus().equals(SourceStatus.CONNECTED)) {
            return;
        }
        cast = new ConnectionManager(queue);
        setState(SourceStatus.CONNECTED);
        log.info("Connected");
    }

    @Override
    protected Observation iNextItem() throws Exception {
        String[] msg = queue.take();
        log.finer(format("Received msg %d from CAST: %s", ++count, Arrays.toString(msg)));
        Observation obs;

        if (msg[0].equals(".")) {
            log.info("Received shutdown from CAST.");
            setsendLastElement();
            obs = new Observation(null, 0L);
        } else {
            Element element = new Element("cast");
            // <cast eventType="ADD" generatorType="src" memoryType="0:null" />    
            element.addAttribute(new Attribute("eventType", msg[1]));
            element.addAttribute(new Attribute("generatorType", msg[2]));
            element.addAttribute(new Attribute("memoryType", msg[3]));
            obs = new Observation(element, Long.parseLong(msg[0]));
            history.add(obs);
        }
        return obs;
    }

    public Observation getObservation(int index) {
        return history.get(index);
    }

}