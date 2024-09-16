## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

link de repo: [link repositorio con el desarrollo](https://github.com/MiltonGutierrez/LAB04.1-ARSW).


En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
		Las dependencias de Spring ya están registradas en el Pom.
	* Agregar la configuración de Spring.
		La configuracion de Spring para soluciobar este punto ya estaba hecha.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.
		Para poder hacer esto, se mantiene la anotacion de @Service a la *BluePrintsServices*, adicionalmente se utiliza la anotación de @Component para la interfaz *InmMemoryBluePrintInterface*, teniendo en cuenta que pueden ghaber más tipos se le añade la anotacion de @Qualifier como se muestra en el siguiente código: 

		```java
		@Component
		@Qualifier("InMemory")
		public class InMemoryBlueprintPersistence implements BlueprintsPersistence{}
		```
		```java
		@Service
		public class BlueprintsServices {}
    		@Autowired
    		@Qualifier("InMemory")
    		BlueprintsPersistence bpp;
		```

2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.
	
	Para poder hacer esto primero que todo añadimos el metodo getBlueprintsByAuthor() en la interfaz *BlueprintsPersistance*, posteriormente agregamos la implementación en *InMemoryBlueprintPersistence*

	- **En BlueprintsPersistance**
	```java
	/**
     * 
     * @param author
     * @return the set containing all blueprints of the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;
	```

	- **En InMemoryBlueprintPersistance**:
	```java
	@Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> blueprintsByAuthor = new HashSet<>();
        for(Blueprint bp: new ArrayList<>(blueprints.values())){
            if(bp.getAuthor().equals(author)){
                blueprintsByAuthor.add(bp);
            }
        }
        if(blueprintsByAuthor.isEmpty()){
            throw new BlueprintNotFoundException("Blueprint not found");
        }
        return blueprintsByAuthor;
    }
	```

	- **En BlueprintsServices**
	```java
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return bpp.getBlueprintsByAuthor(author);
    }

	/**
     * Permite añadir nuevas instancias de BluePrint a la persistencia
     * @param bp
     * @throws BlueprintPersistenceException 
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException{
        bpp.saveBlueprint(bp);
    }
	```

	- **En InMemoryPersistanceTest**:
	```java
	@Test
    public void getBlueprintsByAuthorTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("mack", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.", ibpp.getBlueprintsByAuthor("mack"));

        assertEquals("Method didn't return all of the blueprints.", ibpp.getBlueprintsByAuthor("mack").size(), 2);
        
    }
	```

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 
