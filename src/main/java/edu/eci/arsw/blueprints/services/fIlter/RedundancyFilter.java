package edu.eci.arsw.blueprints.services.filter;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
@Qualifier("Redundancy")
public class RedundancyFilter implements Filter {

    @Override
    public void filterBlueprint(Blueprint blueprint) {
        ArrayList<Point> blueprintPoints = blueprint.getPoints();
        int indexList = 1;
        int indexBase = 0;
        Point basePoint = blueprintPoints.get(indexBase);
        while(indexList < blueprintPoints.size()){
            if(basePoint.equals(blueprintPoints.get(indexList))){
                blueprintPoints.remove(indexBase);
            }
            else{
                indexList++;
                indexBase++;
            }
            basePoint = blueprintPoints.get(indexBase);    
        }
    }
}
