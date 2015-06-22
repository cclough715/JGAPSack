import org.jgap.*;
import org.jgap.impl.*;
/**
 * @author Chad Clough
 * @since 6/21/2015
 */
public class Habitat {
	private int generations;
	private int populationSize;
	private Configuration conf;
	private Genotype population;
	private Item[] inventory;
	private Knapsack sack;
	

	
	@SuppressWarnings("unused")
	private Habitat(){
		//restrict default constructor
	}
	
	public Habitat(Item[] inv){
		try{
			conf = new DefaultConfiguration();
			inventory = inv;
			sack = new Knapsack(inventory.length);
			
			conf.setSampleChromosome(new Chromosome(conf,createSampleGenes()));
			conf.setFitnessFunction(new KnapsackFitnessFunction());
			conf.setPopulationSize(populationSize);
			conf.addNaturalSelector(new BestChromosomesSelector(
				conf, 1.0d), false);
	        conf.addGeneticOperator(new CrossoverOperator(conf));    
		    conf.addGeneticOperator(new MutationOperator(conf));	
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @return
	 */
	private Gene[] createSampleGenes(){
		try{
			Gene[] sampleGenes = new Gene[inventory.length + 1];
			//store the max weight of the knapsack
			sampleGenes[0] = new DoubleGene(conf, sack.getMaxWeight(), 
					sack.getMaxWeight());
			//boolean values to represent whether or not the item is
			//present in the knapsack
			for(int i = 1; i <= inventory.length; i++)
				sampleGenes[i] = new BooleanGene(conf);
			
			return sampleGenes;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Creates a random population, evolves the population generations number
	 * of times and then returns the most fit chromosome. 
	 * 
	 * @return The chromosome in the population with the greatest fitness
	 */
	public IChromosome run(){
		try {
			population = Genotype.randomInitialGenotype(conf);
			for(int i = 0; i < generations; i++) population.evolve();  
			return population.getFittestChromosome();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static void main(String[] args){
		
	}

}
