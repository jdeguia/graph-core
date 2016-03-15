package uk.ac.ebi.reactome.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.reactome.config.MyConfiguration;
import uk.ac.ebi.reactome.domain.model.DatabaseObject;
import uk.ac.ebi.reactome.domain.model.Event;
import uk.ac.ebi.reactome.domain.model.Pathway;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

/**
 * Created by:
 *
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 * @since 09.03.16.
 */
@ContextConfiguration(classes = { MyConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    private static final Logger logger = LoggerFactory.getLogger("testLogger");

    @Autowired
    private Service<DatabaseObject> service;

    @Autowired
    private GenericService genericService;

    @BeforeClass
    public static void setUpClass() {
        logger.info(" --- !!! Running Service !!! --- \n");
    }

    @Before
    public void setUp() throws Exception {
        assumeTrue(genericService.fitForService());
        genericService.clearCache();
    }

    @Test
    public void find() {

        service.find(1L);
    }

    @Test
    public void findWithDepth() {

        service.find(1L,1);
    }

    @Test
    public void saveAndDelete() {

        Pathway pathway = new Pathway();
        pathway.setDbId(111111111111L);
        pathway.setStableIdentifier("R-HSA-111111111111");
        pathway.setDisplayName("TestPathway");

        long count = genericService.countEntries(Pathway.class);
        service.save(pathway);
        long countAfterSave = genericService.countEntries(Pathway.class);
        assertEquals(count + 1, countAfterSave);
        service.delete(genericService.findByDbId(Pathway.class,111111111111L,0).getId());
        long countAfterDelete = genericService.countEntries(Pathway.class);
        assertEquals(count, countAfterDelete);
    }

    @Test
    public void saveAndDeleteWithRelation() {

        Pathway pathway = new Pathway();
        pathway.setDbId(111111111111L);
        pathway.setStableIdentifier("R-HSA-111111111111");
        pathway.setDisplayName("TestPathway");

        Pathway pathway2 = new Pathway();
        pathway2.setDbId(111111111112L);
        pathway2.setStableIdentifier("R-HSA-111111111112");
        pathway2.setDisplayName("TestPathway2");

        Pathway pathway3 = new Pathway();
        pathway3.setDbId(111111111113L);
        pathway3.setStableIdentifier("R-HSA-111111111113");
        pathway3.setDisplayName("TestPathway3");

        List<Event> hasEvent = new ArrayList<>();
        hasEvent.add(pathway2);
        hasEvent.add(pathway3);
        pathway.setHasEvent(hasEvent);

        long count = genericService.countEntries(Pathway.class);
        service.save(pathway,1);
        long countAfterSave = genericService.countEntries(Pathway.class);
        assertEquals(count + 3, countAfterSave);
        service.delete(genericService.findByDbId(Pathway.class,111111111111L,0).getId());
        service.delete(genericService.findByDbId(Pathway.class,111111111112L,0).getId());
        service.delete(genericService.findByDbId(Pathway.class,111111111113L,0).getId());
        long countAfterDelete = genericService.countEntries(Pathway.class);
        assertEquals(count, countAfterDelete);
    }
}