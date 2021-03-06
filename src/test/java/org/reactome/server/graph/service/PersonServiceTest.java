package org.reactome.server.graph.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.reactome.server.graph.domain.model.Pathway;
import org.reactome.server.graph.domain.model.Person;
import org.reactome.server.graph.domain.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 31.05.16.
 */
public class PersonServiceTest extends BaseTest {

    @Autowired
    private PersonService personService;

    @BeforeClass
    public static void setUpClass() {
        logger.info(" --- !!! Running " + PersonServiceTest.class.getName() + " !!! --- \n");
    }

    @Test
    public void findPersonByNameTest() {
        logger.info("Started testing personService.findPersonByName");
        long start, time;
        start = System.currentTimeMillis();
        Collection<Person> persons = personService.findPersonByName("steve jupe");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(1, persons.size());
        logger.info("Finished");
    }

    @Test
    public void queryPersonByNameTest() {
        logger.info("Started testing personService.queryPersonByName");
        long start, time;
        start = System.currentTimeMillis();
        Collection<Person> persons = personService.queryPersonByName("steve jupe");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertTrue(persons.size() >= 14);
        logger.info("Finished");
    }

    @Test
    public void findPersonByOrcidIdTest() {
        logger.info("Started testing personService.findPersonByOrcidIdTest");
        long start, time;
        start = System.currentTimeMillis();
        Person person = personService.findPerson("0000-0001-5807-0069");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals("Jupe, S", person.getDisplayName());
        logger.info("Finished");
    }

    @Test
    public void findPersonByDbIdTest() {
        logger.info("Started testing personService.findPersonByDbIdTest");
        long start, time;
        start = System.currentTimeMillis();
        Person person = personService.findPerson(217030L);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals("Takao, N", person.getDisplayName());
        assertFalse(person.getPublications().isEmpty());
        logger.info("Finished");
    }

    @Test
    public void getPublicationsOfPersonByOrcidIdTest() {
        logger.info("Started testing personService.getPublicationsOfPersonByOrcidId");
        long start, time;
        start = System.currentTimeMillis();
        Collection<Publication> publications = personService.getPublicationsOfPerson("0000-0001-5807-0069");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(3, publications.size());
        logger.info("Finished");
    }

    @Test
    public void getPublicationsOfPersonByDbIdTest() {
        logger.info("Started testing personService.getPublicationsOfPersonByDbId");
        long start, time;
        start = System.currentTimeMillis();
        Collection<Publication> publications = personService.getPublicationsOfPerson(391309L);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertEquals(3, publications.size());
        logger.info("Finished");
    }

//    @Test
//    public void getPublicationsOfPersonByEmailTest() {
//        logger.info("Started testing personService.getPublicationsOfPersonByEmailTest");
//        long start, time;
//        start = System.currentTimeMillis();
//        Collection<Publication> publications = personService.getPublicationsOfPerson("sjupe@ebi.ac.uk");
//        time = System.currentTimeMillis() - start;
//        logger.info("GraphDb execution time: " + time + "ms");
//
//        assertTrue(publications.size() > 0);
//        logger.info("Finished");
//    }

    @Test
    public void getAuthoredPathwaysByOrcidIdTest() {
        logger.info("Started testing personService.getAuthoredPathwaysByOrcidId");
        long start, time;
        start = System.currentTimeMillis();
        Collection<Pathway> pathways = personService.getAuthoredPathways("0000-0001-5807-0069");
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertTrue(pathways.size() >= 65);
        logger.info("Finished");
    }

    @Test
    public void getAuthoredPathwaysByDbIdTest() {
        logger.info("Started testing personService.getAuthoredPathwaysByDbId");
        long start, time;
        start = System.currentTimeMillis();
        Collection<Pathway> pathways = personService.getAuthoredPathways(391309L);
        time = System.currentTimeMillis() - start;
        logger.info("GraphDb execution time: " + time + "ms");

        assertTrue(pathways.size() >= 65);
        logger.info("Finished");
    }

}