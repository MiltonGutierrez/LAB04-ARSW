package edu.eci.arsw.blueprints;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import edu.eci.arsw.blueprints.services.filter.RedundancyFilter;
import edu.eci.arsw.blueprints.services.filter.SubSamplingFilter;
import edu.eci.arsw.blueprints.services.filter.Filter;

public class Main {
    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException{
        /*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bp = ac.getBean(BlueprintsServices.class);
        //Registrar planos
        bp.addNewBlueprint(new Blueprint("Andres", "plano1", new Point[]{new Point(1,2), new Point(3,4)}));
        
        bp.addNewBlueprint(new Blueprint("Andres", "plano2", new Point[]{new Point(5,3), new Point(3,4)}));
        
        bp.addNewBlueprint(new Blueprint("Milton", "plano3", new Point[]{new Point(5,8), new Point(1,4)}));

        //Consultar planos
        
        System.out.println("Consultando todos los planos\n");

        for(Blueprint blueprint: bp.getAllBlueprints()){
            System.out.println(blueprint.toString());
        }
        System.out.println("\n");

        System.out.println("Consultando todos los planos del Autor: Andres\n");
        
        for(Blueprint blueprint: bp.getBlueprintsByAuthor("Andres")){
            System.out.println(blueprint.toString());
        }
        System.out.println("\n");
        System.out.println("Consultando plano especificos\n");
        
        System.out.println(bp.getBlueprint("Milton", "plano3"));*/


        Filter filter = new SubSamplingFilter();
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15),new Point(15, 15),new Point(15, 15),new Point(15, 15), new Point(40, 40),new Point(40, 40),new Point(40, 41), new Point(40, 41)};
        Blueprint bp0 = new Blueprint("mack", "mypaint",pts0);

        filter.filterBlueprint(bp0);

        for(Point pt: bp0.getPoints()){
            System.out.println(pt.toString());
        }
    }
}
