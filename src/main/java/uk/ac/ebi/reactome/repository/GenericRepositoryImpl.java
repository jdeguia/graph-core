package uk.ac.ebi.reactome.repository;

import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 11.11.15.
 */
@Repository
public class GenericRepositoryImpl implements GenericRepository {

    @Autowired
    private Session session;

    @Autowired
    private Neo4jOperations template;

    // implemented this method because SDN 4.1 will add depth parameter
    @Override
    public <T> T loadByProperty(Class<T> clazz, String property, Object value){
        return template.loadByProperty(clazz, property, value);
    }

    @Override
    public <T> T loadById(Class<T> clazz, Long id, Integer depth) {
        return template.load(clazz, id, depth);
    }

    @Override
    public <T> T findByDbId(Class<T> clazz, Long dbId, Integer depth) {
        Collection<T> collection =  session.loadAll(clazz, new Filter("dbId", dbId), depth);
        if (collection!=null && !collection.isEmpty()) {
            return collection.iterator().next();
        }
        return null;
    }

    @Override
    public <T> T findByStId(Class<T> clazz, String stId, Integer depth) {
        Collection<T> collection =  session.loadAll(clazz, new Filter("stId", stId), depth);
        if (collection!=null && !collection.isEmpty()) {
            return collection.iterator().next();
        }
        return null;
    }

    @Override
    public Long countEntries(Class<?> clazz) {
        return template.count(clazz);
    }

    @Override
    public void cleanDatabase() {
        session.purgeDatabase();
    }


//    @Override
//    public DatabaseObject findByDbId(Long dbId, Integer depth) {
//        Collection<DatabaseObject> collection =  session.loadAll(DatabaseObject.class, new Filter("dbId", dbId), depth);
//        if (collection!=null && !collection.isEmpty()) {
//            return collection.iterator().next();
//        }
//        return null;
//    }
//
//    @Override
//    public DatabaseObject findByStId(String stId, Integer depth) {
//        Collection<DatabaseObject> collection =  session.loadAll(DatabaseObject.class, new Filter("stId", stId), depth);
//        if (collection!=null && !collection.isEmpty()) {
//            return collection.iterator().next();
//        }
//        return null;
//    }
}
