package edu.eci.arsw.blueprints.services.filter;

import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;

@Service
@Qualifier("SubSampling")
public class SubSamplingFilter implements Filter{
    
    @Override
    public void filterBlueprint(Blueprint bp) {
        int cont = 0;
        int removed = 0;
        while(cont < bp.getPoints().size()){
            if((cont + 1 + removed) % 3 == 0){
                removed++;
                bp.getPoints().remove(cont);
            }
            cont++;
        }
    }   
}
