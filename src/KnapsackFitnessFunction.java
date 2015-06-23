import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
/**
 * @author Chad Clough
 * @since 6/21/2015
 */
public class KnapsackFitnessFunction extends FitnessFunction {
	private static final long serialVersionUID = 1L;

	@Override
	protected double evaluate(IChromosome arg0) {
		try{
			Gene[] genes = arg0.getGenes();
			double maxWeight = (Double)genes[0].getAllele();
			Knapsack sack = new Knapsack(maxWeight);
			
			for(int i = 1; i < genes.length; i++)
				if((Boolean)genes[i].getAllele())
					//if an item cannot be added then this solution is invalid
					if(!sack.addItem(Habitat.getInventory()[i-1]))
						return 0;
				
			return sack.getCurrentValue();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

}
