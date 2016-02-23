package uk.ac.ebi.reactome.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@SuppressWarnings("unused")
@NodeEntity
public class ReferenceIsoform extends ReferenceGeneProduct {

    private String variantIdentifier;

    @Relationship(type = "isoformParent", direction = Relationship.OUTGOING)
    private List<ReferenceGeneProduct> isoformParent;

    public ReferenceIsoform() {}

    public String getVariantIdentifier() {
        return this.variantIdentifier;
    }

    public void setVariantIdentifier(String variantIdentifier) {
        this.variantIdentifier = variantIdentifier;
    }

    public List<ReferenceGeneProduct> getIsoformParent() {
        return isoformParent;
    }

    public void setIsoformParent(List<ReferenceGeneProduct> isoformParent) {
        this.isoformParent = isoformParent;
    }

}
