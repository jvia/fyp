package org.bham.aucom.fts.tranform;

import nu.xom.Attribute;
import org.bham.aucom.data.Observation;

import java.util.ArrayList;
import java.util.logging.Logger;

public class AddAttributeToElement extends AbstractAucomTranformNode<Observation, Observation> {
    public AddAttributeToElement() {
        super("AddAttributeToElement");
    }

    private ArrayList<Attribute> attributesToAdd = new ArrayList<Attribute>();

    /**
     * @param attr The attributes which will be added to the next xom.Element which
     *             will be piped through this node.
     */
    public synchronized void setAttr(ArrayList<Attribute> attr) {
        this.attributesToAdd.addAll(attr);
    }

    /**
     * @return the attr The attributes which will be added to the next xom.Element
     *         which will be piped through this node.
     */
    public synchronized ArrayList<Attribute> getAttr() {
        return attributesToAdd;
    }

    @Override
    protected Observation iTransform(Observation input) throws Exception {
        Logger.getLogger(this.getClass().getCanonicalName()).info("transformation input : " + input);
        try {
            synchronized (this.attributesToAdd) {
                if (this.attributesToAdd.size() != 0) {
                    for (Attribute attributeToAdd : this.attributesToAdd) {
                        input.addAttribute(attributeToAdd.getLocalName(), attributeToAdd.getValue());
                        input.getContent().addAttribute((Attribute) attributeToAdd.copy());
                    }
                    getAttr().clear();
                }
            }
            Logger.getLogger(this.getClass().getCanonicalName()).info("transformation output : " + input);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getCanonicalName()).severe("error while transforming " + input + " reason " + e.getMessage());
        }
        return input;

    }
}
