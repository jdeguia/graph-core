package org.reactome.server.graph.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark the fields that should not be added in the schema documentation
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReactomeSchemaIgnore {}