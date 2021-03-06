package org.reactome.server.graph.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.reactome.server.graph.domain.annotations.ReactomeSchemaIgnore;

import java.util.List;

/**
 * Shortcut reactions that make the connection between input and output, but don't provide complete mechanistic detail. Used for reactions that do not balance, or complicated processes for which we either don't know all the details, or we choose not to represent every step. (e.g. degradation of a protein)
 */
@SuppressWarnings("unused")
@NodeEntity
public class BlackBoxEvent extends ReactionLikeEvent {

    @ReactomeSchemaIgnore
    @Override
    public String getExplanation() {
        return "Shortcut reactions that make the connection between input and output, but don't provide complete mechanistic detail. " +
                "Used for reactions that do not balance, or complicated processes for which we either don't know all the details, or we choose not to represent every step. (e.g. degradation of a protein)";

    }

    @Relationship(type = "hasEvent")
    private List<Event> hasEvent;

    @Relationship(type = "templateEvent")
    private Event templateEvent;

    public BlackBoxEvent() {}

    public List<Event> getHasEvent() {
        return hasEvent;
    }

    @Relationship(type = "hasEvent")
    public void setHasEvent(List<Event> hasEvent) {
        this.hasEvent = hasEvent;
    }

    public Event getTemplateEvent() {
        return templateEvent;
    }

    @Relationship(type = "templateEvent")
    public void setTemplateEvent(Event templateEvent) {
        this.templateEvent = templateEvent;
    }
}
