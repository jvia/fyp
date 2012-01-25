package org.bham.aucom.fts;

import org.bham.aucom.data.AttributableObject;
import org.bham.aucom.data.LinkEnum;

import java.io.Serializable;
import java.util.*;


public abstract class AbstractLinkableNode implements AttributableObject, Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private final LinkedHashMap<LinkEnum, List<UUID>> links = new LinkedHashMap<LinkEnum, List<UUID>>();

    protected AbstractLinkableNode(UUID id) {
        this.setId(id);
    }

    protected AbstractLinkableNode() {
    }

    private final HashMap<String, String> attributes = new LinkedHashMap<String, String>();

    @Override
    public HashMap<String, String> getAttributes() {
        return this.attributes;
    }

    @Override
    public void addAttribute(String propertyName, String propertyValue) {
        this.attributes.put(propertyName, propertyValue);
    }

    @Override
    public void deleteAttribute(String propertyName) {
        if (this.containsAttribute(propertyName))
            this.attributes.remove(propertyName);
    }

    @Override
    public String getAttributeValue(String propertyName) {
        if (!containsAttribute(propertyName))
            return "";
        return this.attributes.get(propertyName);
    }

    @Override
    public boolean containsAttribute(String propertyName) {
        return this.attributes.containsKey(propertyName);
    }

    public LinkedHashMap<LinkEnum, List<UUID>> getLinks() {
        return this.links;
    }

    protected boolean containsLink(LinkEnum link) {
        return this.links.containsKey(link);
    }

    protected void addLink(LinkEnum link, UUID uuid) {
        if (!this.links.containsKey(link))
            this.links.put(link, new ArrayList<UUID>());
        this.links.get(link).add(uuid);
    }

    public void addLink(LinkEnum link, List<UUID> uuids) {
        if (!this.links.containsKey(link))
            this.links.put(link, new ArrayList<UUID>());
        this.links.get(link).addAll(uuids);
    }

    protected List<UUID> getLinks(LinkEnum link) {
        if (this.links.containsKey(link))
            return this.links.get(link);
        return null;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

}