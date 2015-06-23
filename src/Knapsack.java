import java.util.ArrayList;
/**
 * @author Chad Clough
 * @since 6/21/2015
 */
public class Knapsack {
	private ArrayList<Item> sack;
	private double maxWeight;
	
	/**
	 * Creates an empty Knapsack that can hold a specified weight
	 * 
	 * @param max The max weight this Knapsack can hold
	 * @throws Exception If a non-positive number is passed to max this will
	 * 		throw an exeption
	 */
	public Knapsack(double max) throws Exception{
		if(max <= 0) throw new Exception("Max weight must be positive");
		maxWeight = max;
		sack = new ArrayList<Item>();
		 
	}
	
	/**
	 * Adds an Item to the Knapsack. An Item will not be added to the Knapsack
	 * if the current weight of the Knapsack plus the weight of the new Item
	 * exceeds the maximum weight the Knapsack. 
	 * 
	 * @param i The Item to be added
	 * @return True if the Item was added to the Knapsack; false otherwise
	 */
	public boolean addItem(Item i){
		if(i.getWeight() + getCurrentWeight() > maxWeight)
			return false;
		else{
			sack.add(i);
			return true;
		}
	}
	
	/**
	 * @return The maximum weight that the Knapsack can hold
	 */
	public double getMaxWeight(){
		return maxWeight;
	}
	
	/**
	 * @return The summation of all of the Items' weights in the Knapsack
	 */
	public double getCurrentWeight(){
		double weight = 0;
		for(Item i : sack) weight += i.getWeight();
		return weight;
	}
	
	/**
	 * @return The summation of all of the Items' values in the Knapsack
	 */
	public double getCurrentValue(){
		double value = 0;
		for(Item i : sack) value += i.getValue();
		return value;
	}
}
