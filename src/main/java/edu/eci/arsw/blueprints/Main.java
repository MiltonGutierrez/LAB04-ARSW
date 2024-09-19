package edu.eci.arsw.blueprints;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;


public class Main {
    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException{
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bp = ac.getBean(BlueprintsServices.class);
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15),new Point(15, 15),new Point(15, 15),new Point(15, 15), new Point(40, 40),new Point(40, 40),new Point(40, 41), new Point(40, 41)};
        Blueprint bp0 = new Blueprint("mack", "mypaint",pts0);

        bp.addNewBlueprint(bp0);

        System.out.println("PUNTOS DEL BLUEPRINT ANTES DE EL FILTRADO (SUBSAMPLING): ");
        for(Point pt: bp0.getPoints()){
            System.out.println(pt.toString());
        }
        System.out.println("PUNTOS DEL BLUEPRINT DESPUES DE EL FILTRADO (SUBSAMPLING) (UTILIZANDO UN GET): ");

        for(Point pt: bp.getBlueprint("mack", "mypaint").getPoints()){
            System.out.println(pt.toString());
        }
    }
}
