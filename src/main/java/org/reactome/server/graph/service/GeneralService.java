package org.reactome.server.graph.service;

import org.neo4j.ogm.model.Result;
import org.reactome.server.graph.domain.model.DatabaseObject;
import org.reactome.server.graph.domain.model.ReferenceEntity;
import org.reactome.server.graph.domain.model.Species;
import org.reactome.server.graph.domain.result.ComponentOf;
import org.reactome.server.graph.domain.result.SchemaClassCount;
import org.reactome.server.graph.repository.GeneralTemplateRepository;
import org.reactome.server.graph.repository.GeneralRepository;
import org.reactome.server.graph.service.helper.RelationshipDirection;
import org.reactome.server.graph.service.util.DatabaseObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 15.11.15.
 */
@Service
@SuppressWarnings("WeakerAccess")
public class GeneralService {

    @Autowired
    public GeneralRepository generalRepository;

    @Autowired
    private GeneralTemplateRepository generalTemplateRepository;

    public Integer getDBVersion() {
        return generalRepository.getDBVersion();
    }

    public String getDBName() {
        return generalRepository.getDBName();
    }

    // Gets all schema classes and their counts

    public Collection<SchemaClassCount> getSchemaClassCounts() {
        return generalRepository.getSchemaClassCounts();
    }

    // --------------------------------------.. Generic Query Methods --------------------------------------------------

    public Result query (String query, Map<String,Object> map) {
        return generalTemplateRepository.query(query,map);
    }

    // ------------------------------------------- Save and Delete -----------------------------------------------------

    @SuppressWarnings("UnusedReturnValue")
    public <T extends DatabaseObject> T save(T t) {
        return generalTemplateRepository.save(t);
    }

    @SuppressWarnings("UnusedReturnValue")
    public <T extends DatabaseObject> T save(T t, int depth) {
        return generalTemplateRepository.save(t, depth);
    }

    public void delete (Object o)  {
        generalTemplateRepository.delete(o);
    }

    public void delete(Long dbId) {
        generalTemplateRepository.delete(dbId);
    }

    public void delete(String stId) {
        generalTemplateRepository.delete(stId);
    }

    // ------------------------------------ Utility Methods for JUnit Tests --------------------------------------------

    public void clearCache() {
        generalTemplateRepository.clearCache();
    }

    public boolean fitForService()  {
        return generalTemplateRepository.fitForService();
    }

}
