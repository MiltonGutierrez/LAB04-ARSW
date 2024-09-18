package edu.eci.arsw.blueprints.services.fIlter;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
@Qualifier("Redundancy")
public class RedundancyFilter implements Filter {

    @Override
    public void filterBlueprint(Blueprint blueprint) {
        List<Point> blueprintPoints = blueprint.getPoints();
        Point basePoint = blueprintPoints.get(0);
        for(int i = 1; i < blueprintPoints.size(); i++){
            if(basePoint.equals(blueprintPoints.get(i))){
                blueprintPoints.remove(basePoint);
                basePoint = blueprintPoints.get(i);
            }
            else{
                basePoint = blueprintPoints.get(i);
            }
        }
    }
}
