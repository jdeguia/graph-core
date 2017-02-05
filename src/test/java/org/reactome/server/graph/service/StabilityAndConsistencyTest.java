package org.reactome.server.graph.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reactome.server.graph.domain.model.*;
import org.reactome.server.graph.domain.result.SimpleDatabaseObject;
import org.reactome.server.graph.util.DatabaseObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.junit.Assume.assumeTrue;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class StabilityAndConsistencyTest extends BaseTest {

    private static final String stId = "R-HSA-110243";

    @Autowired
    private DatabaseObjectService dbs;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private SchemaService schemaService;

    @BeforeClass
    public static void setUpClass() {
        logger.info(" --- !!! Running " + StabilityAndConsistencyTest.class.getName() + "!!! --- \n");
    }

    @Override
    @Before
    public void setUp() throws Exception {
        if (!checkedOnce) {
            isFit = generalService.fitForService();
            checkedOnce = true;
        }

        /********   ENABLING LAZY LOADING FOR A PROPER TESTING  *********/
        lazyFetchAspect.setEnableAOP(true);

        assumeTrue(isFit);
        DatabaseObjectFactory.clearCache();
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void libraryStabilityTest() {
        logger.info("Testing libraryStabilityTest");

        Species species = speciesService.getSpeciesByName("Homo sapiens");

        PhysicalEntity activeUnit1;
        {
            ReactionLikeEvent reactionLikeEvent = (ReactionLikeEvent) dbs.findById(stId);
            assumeTrue("wrong size", reactionLikeEvent.getCatalystActivity().size() == 1);
            CatalystActivity catalystActivity = reactionLikeEvent.getCatalystActivity().get(0);
            assumeTrue("wrong size", catalystActivity.getActiveUnit().size() == 1);
            activeUnit1 = catalystActivity.getActiveUnit().iterator().next();
        }

        Collection<SimpleDatabaseObject> complexes = schemaService.getSimpleDatabaseObjectByClass(Complex.class, species);

        PhysicalEntity activeUnit2;
        {
            ReactionLikeEvent reactionLikeEvent = (ReactionLikeEvent) dbs.findById(stId);
            assumeTrue("wrong size", reactionLikeEvent.getCatalystActivity().size() == 1);
            CatalystActivity catalystActivity = reactionLikeEvent.getCatalystActivity().get(0);
            assumeTrue("wrong size", catalystActivity.getActiveUnit().size() == 1);
            activeUnit2 = catalystActivity.getActiveUnit().iterator().next();
        }

        assumeTrue("Active units 1 and 2 should be the same", Objects.equals(activeUnit1, activeUnit2));

        logger.info("Finished");
    }

    @Test
    public void lazyLoadingStoichiometryTest(){
        logger.info("Testing lazyLoadingStoichiometryTest");


        Species species = speciesService.getSpeciesByName("Homo sapiens");

        int comps1 = -1;
        Collection<Complex> complexes = schemaService.getByClass(Complex.class, species);
        for (Complex complex : complexes) {
            if (complex.getStId().equals("R-HSA-83538")) {
                List<PhysicalEntity> hasComponent = complex.getHasComponent();
                comps1 = hasComponent.size();
            }
        }

        Complex complex = (Complex) dbs.findById("R-HSA-83538");
        List<PhysicalEntity> hasComponent = complex.getHasComponent();
        int comps2 = hasComponent.size();

        assumeTrue("Has component should be the same", comps1 == comps2);

        logger.info("Finished");
    }
}