package edu.eci.arsw.blueprints.test.services.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import edu.eci.arsw.blueprints.services.filter.RedundancyFilter;

public class RedundancyFilterTest {
    @Test
    public void filterTest(){
        BlueprintsServices blueprintsServices = new BlueprintsServices();
        RedundancyFilter filter = new RedundancyFilter();
        InMemoryBlueprintPersistence persistence = new InMemoryBlueprintPersistence();

        blueprintsServices.setFilter(filter);
        blueprintsServices.setBlueprintsPersistance(persistence);

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15),new Point(15, 15),new Point(15, 15),new Point(15, 15),new Point(40, 40), new Point(40, 40), new Point(40, 41) };
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);

        try {
            blueprintsServices.addNewBlueprint(bp0);
        } catch (BlueprintPersistenceException e) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        blueprintsServices.getAllBlueprints();

        assertEquals(bp0.getPoints().size(), 4);

        assertEquals(new Point(40, 40),bp0.getPoints().get(0));
        assertEquals(new Point(15, 15),bp0.getPoints().get(1));
        assertEquals(new Point(40, 40),bp0.getPoints().get(2));
        assertEquals(new Point(40, 41),bp0.getPoints().get(3));
    }
}
