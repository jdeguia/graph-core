package org.reactome.server.graph.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@SuppressWarnings("unused")
@NodeEntity
public abstract class AbstractModifiedResidue extends DatabaseObject {

    @Relationship(type = "referenceSequence")
    private ReferenceSequence referenceSequence;

    public AbstractModifiedResidue() {}

    public ReferenceSequence getReferenceSequence() {
        return referenceSequence;
    }

    @Relationship(type = "referenceSequence")
    public void setReferenceSequence(ReferenceSequence referenceSequence) {
        this.referenceSequence = referenceSequence;
    }

}
