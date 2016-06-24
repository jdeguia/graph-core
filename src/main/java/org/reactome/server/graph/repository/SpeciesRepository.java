package org.reactome.server.graph.repository;

import org.reactome.server.graph.domain.model.Species;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@Repository
public interface SpeciesRepository extends GraphRepository<Species> {

    @Query("MATCH (n:Species)<-[:species]-(:TopLevelPathway) RETURN n ORDER BY n.displayName")
    List<Species> getSpecies();

    @Query("MATCH (n:Species) RETURN n ORDER BY n.displayName")
    List<Species> getAllSpecies();
}