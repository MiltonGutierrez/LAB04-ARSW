package edu.eci.arsw.blueprints.services.fIlter;

import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;

@Service
@Qualifier("SubSampling")
public class SubSamplingFilter implements Filter{
    
    @Override
    public void filterBlueprint(Blueprint bp) {
        int cont = 1;
        while(cont < bp.getPoints().size()){
            if(cont % 3 == 0){
                bp.getPoints().remove(cont - 1);
            }
            cont++;
        }
    }   
}
