import org.jgap.*;
import org.jgap.impl.*;
/**
 * @author Chad Clough
 * @since 6/21/2015
 */
public class Habitat {
	private int generations = 400;
	private int populationSize = 100;
	private Configuration conf;
	private Genotype population;
	private static Item[] inventory;
	private Knapsack sack;
	
	@SuppressWarnings("unused")
	private Habitat(){
		//restrict default constructor
	}
	
	public Habitat(Item[] inv, double maxWeight){
		try{
			conf = new DefaultConfiguration();
			inventory = inv;
			sack = new Knapsack(maxWeight);
			
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
	 * @return sample genes for a knapsack chromosome. Used for setting a
	 * 	sample chromosome for a jgap Configuration object
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
	
	/**
	 * Creates a string that is an easy to read version of a solution.
	 * 
	 * Example Output
	 * --------------------------
	 * Solution:
	 * value = 32.0	weight = 7.0
	 *
	 * Item	Value	Weight	Density
	 * 2	20.0	1.0		20.0
	 * 4	9.0		5.0		1.8
	 * 5	3.0		1.0		3.0
	 * 
	 * @param solution The solution to prettify
	 * @return A string of a solution that provides information on the items
	 * 	in the solution as well as making the solution easy to read
	 */
	public static String prettifySolution(IChromosome solution){
		Gene[] genes = solution.getGenes();
		String ret = "Solution:\nvalue = ";
		String items = "\n\nItem\tValue\tWeight\tDensity";
		
		double value = 0, weight = 0;
		Item item;
		for(int i = 1; i < genes.length; i++){
			item = inventory[i-1];
			if((Boolean)genes[i].getAllele()){
				items += "\n" + (i-1) + "\t" + item.toString();
				value += item.getValue();
				weight += item.getWeight();
			}
		}
		
		ret += "" + value + "\tweight = " + weight + items;
		
		return ret;
	}
	
	public static Item[] getInventory(){
		return inventory;
	}
	
	public static void main(String[] args){
		//display problem
		System.out.println("This knapsack can hold 10 lbs.");
		System.out.println("Here's the list of items:\n"
				+ "Value\tWeight\tDensity");
		String items = "3:5;1:20;20:1;2:4;9:5;3:1;15:10";
		Item[] inv = Item.parseItems(items);
		for(Item i : inv) System.out.println(i);
		
		//calculate solution
		System.out.println("\nCreating a new habitat...");
		Habitat habitat = new Habitat(inv, 10);
		System.out.println("Getting a solution...\n");
		IChromosome solution = habitat.run();
		System.out.println(prettifySolution(solution));
	}

}
