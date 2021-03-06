package org.reactome.server.graph.domain.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.reactome.server.graph.domain.annotations.ReactomeProperty;

import java.util.List;

@SuppressWarnings("unused")
@NodeEntity
public class InstanceEdit extends DatabaseObject {

    @ReactomeProperty
    private String dateTime;
    @ReactomeProperty
    private String note;

    @Relationship(type = "author", direction = Relationship.INCOMING)
    private List<Person> author;

    public InstanceEdit() {}

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Relationship(type = "author", direction = Relationship.INCOMING)
    public List<Person> getAuthor() {
        return author;
    }

    @Relationship(type = "author", direction = Relationship.INCOMING)
    public void setAuthor(List<Person> author) {
        this.author = author;
    }

}
