package uk.ac.ebi.reactome.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * BlackBoxEvent contains "unbalanced" reactions like synthesis or degradation and "shortcut" reactions representing
 * more complex processes
 */
@SuppressWarnings("unused")
@NodeEntity
public class BlackBoxEvent extends ReactionLikeEvent {

    @Relationship(type = "hasEvent", direction = Relationship.OUTGOING)
    private List<Event> hasEvent;

    @Relationship(type = "templateEvent", direction = Relationship.OUTGOING)
    private Event templateEvent;

    public BlackBoxEvent() {}

    public List<Event> getHasEvent() {
        return hasEvent;
    }

    public void setHasEvent(List<Event> hasEvent) {
        this.hasEvent = hasEvent;
    }

    public Event getTemplateEvent() {
        return templateEvent;
    }

    public void setTemplateEvent(Event templateEvent) {
        this.templateEvent = templateEvent;
    }
}
