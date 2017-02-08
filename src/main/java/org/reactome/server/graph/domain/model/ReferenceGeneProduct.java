package org.reactome.server.graph.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.reactome.server.graph.domain.annotations.ReactomeProperty;

import java.util.List;

@SuppressWarnings("unused")
@NodeEntity
public class ReferenceGeneProduct extends ReferenceSequence {

    @ReactomeProperty
    private List<String> chain;

    @Relationship(type = "referenceGene", direction = Relationship.OUTGOING)
    private List<ReferenceDNASequence> referenceGene;

    @Relationship(type = "referenceTranscript", direction = Relationship.OUTGOING)
    private List<ReferenceRNASequence> referenceTranscript;

    public ReferenceGeneProduct() {}

    public List<String> getChain() {
        return chain;
    }

    public void setChain(List<String> chain) {
        this.chain = chain;
    }

    public List<ReferenceDNASequence> getReferenceGene() {
        return referenceGene;
    }

    @Relationship(type = "referenceGene", direction = Relationship.OUTGOING)
    public void setReferenceGene(List<ReferenceDNASequence> referenceGene) {
        this.referenceGene = referenceGene;
    }

    public List<ReferenceRNASequence> getReferenceTranscript() {
        return referenceTranscript;
    }

    @Relationship(type = "referenceTranscript", direction = Relationship.OUTGOING)
    public void setReferenceTranscript(List<ReferenceRNASequence> referenceTranscript) {
        this.referenceTranscript = referenceTranscript;
    }

    
}
