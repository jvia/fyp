package org.bham.system.cast;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import nu.xom.Attribute;
import nu.xom.Element;

import org.bham.aucom.data.Observation;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.AucomSourceAdapter;
import org.bham.aucom.fts.source.SourceStatus;


/**
 *
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class CastObservationSource extends AucomSourceAdapter<Observation> {

    private ConnectionManager cast;
    private LinkedBlockingQueue<String[]> queue;

    public CastObservationSource()
    {
        super("CastObservationSource");
        queue = new LinkedBlockingQueue<String[]>();
    }

    @Override
    protected void iDisconnect() throws ActionFailedException
    {
        cast.shutdown();
        setState(SourceStatus.DISCONNECTED);
    }

    @Override
    protected void iConnect() throws ActionFailedException
    {
        if (getStatus().equals(SourceStatus.CONNECTED))
            return;
        cast = new ConnectionManager(queue);
        setState(SourceStatus.CONNECTED);
    }

    @Override
    protected Observation iNextItem() throws Exception
    {
        String[] msg = queue.take();
        Observation obs;
        
        if (msg[0].equals(".")) {
            setsendLastElement();
            obs = new Observation(null, 0L);
        } else {
            Element element = new Element("cast");
            // <cast eventType="ADD" generatorType="src" memoryType="0:null" />    
            element.addAttribute(new Attribute("eventType", msg[1]));
            element.addAttribute(new Attribute("generatorType", msg[2]));
            element.addAttribute(new Attribute("memoryType", msg[3]));
            obs = new Observation(element, Long.parseLong(msg[0]));
        }
        return obs;
    }
    
    private void log(Level level, String msg, Object ex)
    {
        Logger.getLogger(CastObservationSource.class.getName()).log(level, msg, ex);
    }
}