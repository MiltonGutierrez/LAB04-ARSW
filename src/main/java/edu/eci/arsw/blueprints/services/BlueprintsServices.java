/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.services.fIlter.Filter;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
    @Autowired
    @Qualifier("Redundancy")
    Filter filter;
   
    @Autowired
    @Qualifier("inMemory")
    BlueprintsPersistence blueprintsPersistence;
    
    /**
     * Sets the BlueprintsPersistance.
     * @param bpp
     */
    public void setBlueprintsPersistance(BlueprintsPersistence bpp){
        this.blueprintsPersistence = bpp;
    }

    /**
     * 
     * @return the blueprintspersistance.
     */
    public BlueprintsPersistence getBlueprintsPersistence(){
        return blueprintsPersistence;
    }

    /**
     * Sets the filter to use
     * @param flt
     */
    public void setFilter(Filter flt){
        this.filter = flt;
    }

    /**
     * Adds the blueprint using the BlueprintPersistance method.
     * @param bp
     * @throws BlueprintPersistenceException 
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        blueprintsPersistence.saveBlueprint(bp);
    }
    
    /**
     * 
     * @return a set with all the blueprints saved.
     */
    public Set<Blueprint> getAllBlueprints(){
        for(Blueprint bp: blueprintsPersistence.getAllBlueprints()){
            filter.filterBlueprint(bp);
        }
        return blueprintsPersistence.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException {
        Blueprint blueprint = blueprintsPersistence.getBlueprint(author, name);
        filter.filterBlueprint(blueprint);
        return blueprint;
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        for(Blueprint bp: blueprintsPersistence.getBlueprintsByAuthor(author)){
            filter.filterBlueprint(bp);
        }
        return blueprintsPersistence.getBlueprintsByAuthor(author);
    }
    
}
