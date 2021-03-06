package org.reactome.server.graph.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.reactome.server.graph.domain.annotations.ReactomeProperty;

@SuppressWarnings("unused")
@NodeEntity
public class TranslationalModification extends AbstractModifiedResidue {

    @ReactomeProperty
    private Integer coordinate;

    @Relationship(type = "psiMod")
    private PsiMod psiMod;
    
    public TranslationalModification() {}

    public Integer getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Integer coordinate) {
        this.coordinate = coordinate;
    }

    public PsiMod getPsiMod() {
        return psiMod;
    }

    @Relationship(type = "psiMod")
    public void setPsiMod(PsiMod psiMod) {
        this.psiMod = psiMod;
    }
    
}
